package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.account.domain.Account;
import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.candle.service.DayCandleSearch;
import com.bitcoin.autotrading.candle.service.GetRsiByDay;
import com.bitcoin.autotrading.order.domain.Order;
import com.bitcoin.autotrading.user.domain.ResponseBackTestingDTO;
import com.mchange.v2.lang.ObjectUtils;
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

@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Component
public class BackTestingService {

    @Autowired
    GetRsiByDay getRsiByDay;

    @Autowired
    private DayCandleSearch dayCandleSearch;

    public String srt_dttm;
    public String end_dttm;
    public int state;

    /**
     * 백테스팅용
     */
    public ResponseBackTestingDTO backTesting() throws IOException, InterruptedException, JSONException, ParseException {
        this.srt_dttm = "2023-02-01 09:00:00";

        // 매수는 1회 매수금액으로
        // 매도는 RSI 충족시 전량 매도 -> 추후 수정
        int uuid = 0; //주문ID
        int deposit = 30000; // 예수금
        int moneybytime = 5000; // 1회 매수 금액
        int rsisellcond = 45;   // 매도조건 rsi > 70
        int rsibuycond = 30;   // 매수조건 rsi < 30

        // 데이터 조회용
        List<Candle> list = dayCandleSearch.dayCandleSearch(srt_dttm);

        List<Order> orderList = new ArrayList<>();
        Account account = null;
        // rsi값 조회하기 i는 조회 갯수
        for (int i = 0; i < 60; i++) {
            double rsi = getRsiByDay.GetRsiBy(this.srt_dttm);

            list.get(i).setRsi(rsi);

            int trade_price = Integer.parseInt(list.get(i).getTrade_price().toString()); //현재가

            // 매수
            if (rsi > rsisellcond) {
                if (deposit >= moneybytime) {

                    //주문 내역 생성
                    orderList.add(Order.builder()
                            .uuid(uuid)
                            .market("BTC")
                            .price(moneybytime)
                            .ord_type("price") //시장가주문 - 매수
                            .state("done")     //완료
                            .side("bid")      //매수
                            .volume( String.format("%8f", (double) moneybytime / trade_price))
                            .created_at(srt_dttm)
                            .build());


                    //잔고 생성
                    if (account == null) {
                        log.info("moneybytime="+moneybytime);
                        log.info("trade_price="+trade_price);
                        log.info("moneybytime/trade_price="+String.format("%10f", (double) moneybytime / trade_price));
                        account = Account.builder()
                                .currency("BTC")
                                .balance(String.format("%10f", (double) moneybytime / trade_price))
                                .avg_buy_price(trade_price) //평단
                                .unit_currency("KRW") //평단 기준 화폐 - KRW
                                .build();
                        log.info(account.toString());

                    } else {

                        double balance = (double) moneybytime / trade_price;
                        //평단계산 = (n차 매수 가격 * 수량 + (n+1)차 매수 가격) / 전체 수량
                        int avg_trade_price = (int)((account.getAvg_buy_price() * Double.valueOf(account.getBalance())
                                + moneybytime)
                                / (Double.valueOf(account.getBalance()) + balance));

                        account.setBalance( String.format("%10f",balance + Double.parseDouble(account.getBalance()))); //수량
                        account.setAvg_buy_price(avg_trade_price);
                    }

                    log.info("매수 - 잔고 :"+account.toString());
                    log.info("매수 - 내역 :"+orderList.get(uuid).toString());

                    deposit = deposit - moneybytime;
                    uuid++;

                } else {
                    log.info("매수 예수금이 부족합니다. rsi=["+rsi+"]");
                }
            } else if (rsi < rsibuycond) {
                //잔고가있으면 전량매도
                if (account != null) {
                    // 수익률 = 매도평균가 / 매수평균가
                    double portfolio = Double.valueOf(list.get(i).getTrade_price().toString()) / account.getAvg_buy_price() *100 -100;

                    // 예수금
                    deposit = (int)(Double.valueOf(account.getBalance()) * Integer.parseInt(list.get(i).getTrade_price().toString()));

                    orderList.add(Order.builder()
                            .uuid(uuid)
                            .market("BTC")
                            .price(deposit)
                            .volume(account.getBalance())
                            .ord_type("market") //시장가주문 - 매도
                            .state("done")     //완료
                            .side("ask")      //매도
                            .created_at(srt_dttm)
                            .portfolio(String.format("%10f",portfolio))
                            .build());

                    log.info("매도 - 내역 :"+orderList.get(uuid).toString());

                    account = null;
                    uuid++;

                }

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
        log.info("account="+account.toString());

        return ResponseBackTestingDTO.builder()
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
