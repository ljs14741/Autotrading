package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.candle.service.GetRsiByMinutes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
@Slf4j
public class FromToTrading {

    @Autowired
    public GetRsiByMinutes getRsiByMinutes;

    public String srt_dttm;
    public String end_dttm;
    public int state;


    @Builder
    public FromToTrading(String srt_dttm, String end_dttm, int state){

        /*
            마지막 캔들 시각 (exclusive).
            ISO8061 포맷 (yyyy-MM-dd'T'HH:mm:ss'Z' or yyyy-MM-dd HH:mm:ss). 기본적으로 UTC 기준 시간이며 2023-01-01T00:00:00+09:00 과 같이 KST 시간으로 요청 가능.
            비워서 요청시 가장 최근 캔들
        */
        this.srt_dttm = srt_dttm;
        this.end_dttm = end_dttm;
        this.state = state;
    }


    /**
     * 백테스팅용
     */
    public void BackTesting(){

        try {

            Date to_date_end = new SimpleDateFormat("yyyyMMddHHmm").parse(this.end_dttm);
            Date to_date_srt = new SimpleDateFormat("yyyyMMddHHmm").parse(this.srt_dttm);

            long diffSec = (to_date_end.getTime() - to_date_srt.getTime()) / 1000; // 초 차이
            long time = diffSec / (60 * 30); //예시 - 30분봉기준

            log.info("diffSec =["+diffSec+"]");
            log.info("time =["+time+"]");

            for (int i = 0; i < time; i++) {
                this.Process();
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void Process(){

        //   주문 체결 후 처리 - 가잔고 테이블에 insert
        //1. 손익계산 및 잔고정리 (기간 수익을 알기 위해서) - 업비트 API는 해당 정보가 없다. 직접해야함


        //2. RSI 계산(지표 계산) -- 실제 계산
        getRsiByMinutes.main(); // RSI 계산

        //3. 조건 검증(지금은 RSI 값이 >30 인 로직을 넣을예정)
        int order = new ValidationOrder().validation();

        //4. 매매 (주문가능조회 -> 주문) --가매매
        // order - 만들어줘 진수야


    }


}
