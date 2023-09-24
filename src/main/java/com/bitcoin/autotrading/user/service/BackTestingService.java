package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.account.domain.Account;
import com.bitcoin.autotrading.account.domain.dto.AccountDto;
import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.candle.domain.dto.CandleDto;
import com.bitcoin.autotrading.candle.service.DayCandleSearch;
import com.bitcoin.autotrading.candle.service.GetRsiByDay;
import com.bitcoin.autotrading.order.domain.Order;
import com.bitcoin.autotrading.order.domain.dto.OrderDto;
import com.bitcoin.autotrading.order.domain.service.OrderService;
import com.bitcoin.autotrading.user.domain.ResponseBackTestingDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Slf4j
@Component
public class BackTestingService {

    @Autowired
    GetRsiByDay getRsiByDay;

    @Autowired
    private DayCandleSearch dayCandleSearch;

    @Autowired
    private OrderService orderService;

    public String srt_dttm;
    public String end_dttm;
    public int state;


    /**
     * 백테스팅용
     */
    public ResponseBackTestingDto backTesting() throws IOException, InterruptedException, JSONException, ParseException, IllegalAccessException {

        this.srt_dttm = "2023-03-01 09:00:00";

        int uuid = 0; //주문ID
        double deposit = 30000; // 예수금
        int moneybytime = 5000; // 1회 매수 금액
        int rsisellcond = 50;   // 매도조건 rsi > 70
        int rsibuycond = 30;   // 매수조건 rsi < 30

        double TakeProfitRate = 5.5; //익절률
        double StopLossRate = -5.5; //손절률

        // 데이터 조회용
        List<CandleDto> list = dayCandleSearch.dayCandleSearch(srt_dttm);

        List<OrderDto> orderList = new ArrayList<>();
        AccountDto account = null;

        for (int i = 0; i < 30; i++) {
            double rsi = getRsiByDay.GetRsiBy(this.srt_dttm);
            list.get(i).setRsi(rsi);
            double trade_price = list.get(i).getTrade_price(); //현재가

            // 매수
            if (rsi < rsibuycond) {
                if (deposit >= moneybytime) {
                    //주문 내역 생성
                    orderList.add(OrderDto.builder()
                            .uuid(String.valueOf(uuid))
                            .market("BTC")
                            .price((double) moneybytime)
                            .ord_type("price") //시장가주문 - 매수
                            .state("done")     //완료
                            .side("bid")      //매수
                            .volume((double) moneybytime / trade_price)
                            .created_at(srt_dttm)
                            .build());



                    //잔고 생성
                    if (account == null) {

                        account = AccountDto.builder()
                                .currency("BTC")
                                .balance((double) moneybytime / trade_price)
                                .avg_buy_price((double) trade_price) //평단
                                .unit_currency("KRW") //평단 기준 화폐 - KRW
                                .build();

                    } else {
                        double balance = (double) moneybytime / trade_price;
                        //평단계산 = (n차 매수 가격 * 수량 + (n+1)차 매수 가격) / 전체 수량
                        double avg_trade_price = (account.getAvg_buy_price() * account.getBalance()
                                + moneybytime)
                                / (account.getBalance() + balance);

                        account.setBalance( balance + account.getBalance()); //수량
                        account.setAvg_buy_price(avg_trade_price);
                    }

                    deposit = deposit - moneybytime;
                    uuid++;

                } else {
                    log.info("매수 예수금이 부족합니다. rsi=["+rsi+"]");
                }
            } else if (rsi > rsisellcond) {
                //잔고가있으면
                if (account != null) {

                    // 수익률 = 매도평균가 / 매수평균가
                    double portfolio = list.get(i).getTrade_price() / account.getAvg_buy_price() *100 -100;

                    // 예수금
                    deposit = account.getBalance() * list.get(i).getTrade_price();

                    orderList.add(OrderDto.builder()
                            .uuid(String.valueOf(uuid))
                            .market("BTC")
                            .price(deposit)
                            .volume(account.getBalance())
                            .ord_type("market") //시장가주문 - 매도
                            .state("done")     //완료
                            .side("ask")      //매도
                            .created_at(srt_dttm)
                            .portfolio(portfolio)
                            .build());

                    account = null;
                    uuid++;

                }

            }else{

            }

            SimpleDateFormat sdfYMDHms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdfYMDHms.parse(this.srt_dttm);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, +1); // 1일 더하기
            this.srt_dttm = sdfYMDHms.format(cal.getTime());

        }
        for (int i = 0; i < orderList.size(); i++) {
            log.info("orderlist="+orderList.get(i).toString());
        }
        if (account!=null)
            log.info("account="+account.toString());

        for (OrderDto orderDto:orderList) {
            orderService.save(Order.toEntity(orderDto));
        }


        return ResponseBackTestingDto.builder()
                .candleList(list)
                .orderList(orderList)
                .account(account)
                .build();

    }
//        try {
//            this.end_dttm = "202309030900";
//            this.srt_dttm = "202309031200";
//            Date to_date_end = new SimpleDateFormat("yyyyMMddHHmm").parse(this.end_dttm);
//            Date to_date_srt = new SimpleDateFormat("yyyyMMddHHmm").parse(this.srt_dttm);
//
//
//            long diffSec = (to_date_end.getTime() - to_date_srt.getTime()) / 1000; // 초 차이
//            long time = (long)(diffSec / (60 * 30)); //예시 - 30분봉기준
//
//            log.info("diffSec =["+diffSec+"]");
//            log.info("time =["+time+"]");
//
//            for (int i = 0; i < time; i++) {
//                this.process();
//            }
//
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//    }

//    public void process(){
//
//        //1. RSI 계산(지표 계산) -- 실제 계산
//        getRsiByMinutes.main(); // RSI 계산
//
//        //2. 조건 검증(지금은 RSI 값이 >30 인 로직을 넣을예정)
//        int order = new ValidationOrder().validation();
//
//        //3. 매매 (주문가능조회 -> 주문) --
//        // order - 만들어줘 진수야
//
//        //   주문 체결 후 처리 - 가잔고 테이블에 insert
//        //4. 손익계산 및 잔고정리 (기간 수익을 알기 위해서) - 업비트 API는 해당 정보가 없다. 직접해야함
//        // 개별주문조회 API 정리해줘야 함 체결됬는지 안됐는지
//        // API 타고
//
//    }


}
