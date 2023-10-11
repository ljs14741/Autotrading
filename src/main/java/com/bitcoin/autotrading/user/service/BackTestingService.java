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
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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

    /**
     * 백테스팅용
     */
    public ResponseBackTestingDTO backTesting(UserCondition userCondition){


        List<OrderDTO> orderDTOList = null;
        AccountDTO accountDTO = null;

        try {

            SimpleDateFormat sdfYMDHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endDttm = sdfYMDHms.parse(userCondition.getSrtDttm());
            Date srtDttm = sdfYMDHms.parse(userCondition.getEndDttm());
            this.currentDateString = sdfYMDHms.format(srtDttm.getTime());

            long diffSec = (endDttm.getTime() - srtDttm.getTime()) / (1000 * 60); // 분 차이
            long time = (long)(diffSec/30); //예시 - 30분봉기준
            Calendar cal = Calendar.getInstance();

            for (int i = 0; i < time; i++) {
                this.process(userCondition, orderDTOList, accountDTO);
                cal.setTime(srtDttm);
                cal.add(Calendar.MINUTE, +30); //30분 봉이기 때문에 30분 더하기
                currentDateString = sdfYMDHms.format(cal.getTime());
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }

        return ResponseBackTestingDTO.builder()
                .orderList(orderDTOList)
                .account(accountDTO)
                .build();
    }


    public void process(UserCondition userCondition, List<OrderDTO> list, AccountDTO accountDTO) throws Exception{


        //1. RSI 계산
        CandleDTO candleDTO = getIndex(userCondition);

        //2. 조건 검증
        int isOrder = validation(candleDTO, userCondition);

        //3. 주문

        //4. 거래내역 생성
        if(isOrder !=0)
            makeTransaction(candleDTO, userCondition, isOrder, list, accountDTO);

    }

    public CandleDTO getIndex(UserCondition userCondition) throws Exception{

        double rsi = getRsi.getRsi(currentDateString,"minutes/30",userCondition.getMarket());
        CandleDTO candleDTO = getCandle.getCandle(currentDateString,"minutes/30",userCondition.getMarket(),1).get(0);
        candleDTO.setRsi(rsi);
        return candleDTO;

    }

    public int validation(CandleDTO candleDTO, UserCondition userCondition){

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

    public void makeTransaction(CandleDTO candleDTO, UserCondition userCondition,int isOrder, List<OrderDTO> orderList, AccountDTO account) {

        double tradePrice = candleDTO.getTradePrice(); //현재가
        double moneyByTime = (double) userCondition.getDeposit() / userCondition.getBuyCnt(); //1회 매수 금액

        //매수
        if(isOrder == 2){

            orderList.add(OrderDTO.builder()
                    .uuid(String.valueOf(orderList.size()+1))
                    .market(userCondition.getMarket())
                    .price((double) moneyByTime)
                    .ordType("price") //시장가주문 - 매수
                    .state("done")     //완료
                    .side("bid")      //매수
                    .volume((double) moneyByTime / tradePrice)
                    .createdAt(currentDateString)
                    .build());

            if (account == null) {
                account = AccountDTO.builder()
                        .currency(userCondition.getMarket())
                        .balance((double) moneyByTime / tradePrice)
                        .avgBuyPrice((double) tradePrice) //평단
                        .unitCurrency(userCondition.getMarket())
                        .build();

            } else {
                double balance = (double) moneyByTime / tradePrice;
                //평단계산 = (n차 매수 가격 * 수량 + (n+1)차 매수 가격) / 전체 수량
                double avg_trade_price = (account.getAvgBuyPrice() * account.getBalance()
                        + moneyByTime)
                        / (account.getBalance() + balance);

                account.setBalance( balance + account.getBalance()); //수량
                account.setAvgBuyPrice(avg_trade_price);

            }

        }else {

            //매도
            if (account != null) {

                // 수익률 = 매도평균가(현재가) / 매수평균가
                double portfolio = tradePrice / account.getAvgBuyPrice() *100 -100;

                orderList.add(OrderDTO.builder()
                        .uuid(String.valueOf(orderList.size()+1))
                        .market(userCondition.getMarket())
                        .price(account.getBalance() * tradePrice)
                        .volume(account.getBalance())
                        .ordType("market") //시장가주문 - 매도
                        .state("done")     //완료
                        .side("ask")      //매도
                        .createdAt(currentDateString)
                        .portfolio(portfolio)
                        .build());

                account = null;
            }
        }

    }

}
