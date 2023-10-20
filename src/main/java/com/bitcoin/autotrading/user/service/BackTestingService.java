package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.account.domain.dto.AccountDTO;
import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.service.GetCandle;
import com.bitcoin.autotrading.candle.service.GetRsi;
import com.bitcoin.autotrading.order.domain.dto.OrderDTO;
import com.bitcoin.autotrading.order.domain.service.OrderService;
import com.bitcoin.autotrading.user.domain.entity.UserCondition;
import com.bitcoin.autotrading.user.domain.dto.ResponseBackTestingDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@NoArgsConstructor
@Getter
@Slf4j
@Component
public class BackTestingService {

    @Autowired
    GetRsi getRsi;

    @Autowired
    private GetCandle getCandle;

    @Autowired
    private OrderService orderService;

    private String currentDateString;

    private List<OrderDTO>  orderDTOList;
    private AccountDTO accountDTO;
    private UserCondition userCondition;
    private CandleDTO candleDTO;

    /**
     * 백테스팅용
     */
    public ResponseBackTestingDTO backTesting(UserCondition userCondition) throws Exception{

        this.userCondition = userCondition;
        this.orderDTOList = new ArrayList<>();
        this.accountDTO = null;

        LocalDateTime curruentDate = userCondition.getSrtDttm();
        this.currentDateString = userCondition.getSrtDttm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        Duration duration = Duration.between(userCondition.getSrtDttm(), userCondition.getEndDttm());
        long diffSec = duration.getSeconds();

        int unit;
        if(userCondition.getUnit().equals("MIN")){
            unit=1;
        } else if (userCondition.getUnit().equals("DAY")) {
            unit=1440;
        } else if (userCondition.getUnit().equals("MON")) {
            unit=43800;
        } else {
            unit=0;
        }

        long time = diffSec/(unit * userCondition.getUnitVal() * 60);
        log.info(String.valueOf(time));
        for (int i = 0; i < time; i++) {
            this.process();
            curruentDate = curruentDate.plusMinutes(unit * userCondition.getUnitVal());
            log.info(curruentDate.toString());
            this.currentDateString = curruentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            log.info(currentDateString);
        }

        for (int i = 0; i < orderDTOList.size(); i++) {
            log.info(orderDTOList.get(i).toString());
        }
        log.info(accountDTO.toString());


        return ResponseBackTestingDTO.builder()
                .orderList(orderDTOList)
                .account(accountDTO)
                .build();
    }


    public void process() throws Exception{


        //1. RSI 계산
        CandleDTO candleDTO = getIndex();

        //2. 조건 검증
        int isOrder = validation();

        //3. 주문

        //4. 거래내역 생성
        if(isOrder !=0)
            makeTransaction(isOrder);

    }

    public CandleDTO getIndex() throws Exception{

        double rsi = getRsi.getRsi(currentDateString,"minutes/30",userCondition.getMarket());
        candleDTO = getCandle.getCandle(currentDateString,"minutes/30",userCondition.getMarket(),1).get(0);
        candleDTO.setRsi(rsi);
        log.info(currentDateString+" : "+rsi);
        return candleDTO;

    }

    public int validation(){

        double rsiBuyCond = userCondition.getBuyCondition();
        double rsiSellCond = userCondition.getSellCondition();
        double rsi = candleDTO.getRsi();

        if (rsi < rsiBuyCond) {
            return 2;  //매수
        } else if (rsi > rsiSellCond) {
            return 1;  //매도
        }
        return 0;      //없음
    }

    public void makeTransaction(int isOrder) {

        double tradePrice = candleDTO.getTradePrice(); //현재가
        double moneyByTime = (double) userCondition.getDeposit() / userCondition.getBuyCnt(); //1회 매수 금액

        //매수
        if(isOrder == 2){

            orderDTOList.add(OrderDTO.builder()
                    .uuid(String.valueOf(orderDTOList.size()+1))
                    .market(userCondition.getMarket())
                    .price(moneyByTime)
                    .ordType("price") //시장가주문 - 매수
                    .state("done")     //완료
                    .side("bid")      //매수
                    .volume(moneyByTime / tradePrice)
                    .createdAt(currentDateString)
                    .build());

            if (accountDTO == null) {
                accountDTO = AccountDTO.builder()
                        .currency(userCondition.getMarket())
                        .balance(moneyByTime / tradePrice)
                        .avgBuyPrice(tradePrice) //평단
                        .unitCurrency(userCondition.getMarket())
                        .build();

            } else {
                double balance = moneyByTime / tradePrice;
                //평단계산 = (n차 매수 가격 * 수량 + (n+1)차 매수 가격) / 전체 수량

                log.info(accountDTO.toString());
                double avg_trade_price = (accountDTO.getAvgBuyPrice() * accountDTO.getBalance()
                        + moneyByTime)
                        / (accountDTO.getBalance() + balance);

                accountDTO.setBalance( balance + accountDTO.getBalance()); //수량
                accountDTO.setAvgBuyPrice(avg_trade_price);

            }

        }else {

            //매도
            if (accountDTO != null) {

                // 수익률 = 매도평균가(현재가) / 매수평균가
                double portfolio = tradePrice / accountDTO.getAvgBuyPrice() * 100-100;

                orderDTOList.add(OrderDTO.builder()
                        .uuid(String.valueOf(orderDTOList.size()+1))
                        .market(userCondition.getMarket())
                        .price(accountDTO.getBalance() * tradePrice)
                        .volume(accountDTO.getBalance())
                        .ordType("market") //시장가주문 - 매도
                        .state("done")     //완료
                        .side("ask")      //매도
                        .createdAt(currentDateString)
                        .portfolio(portfolio)
                        .build());

                accountDTO = null;
            }
        }

    }

}
