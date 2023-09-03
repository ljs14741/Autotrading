package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.candle.service.GetRsiByMinutes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Component
public class BackTestingService {

    @Autowired
    private GetRsiByMinutes getRsiByMinutes;

    public String srt_dttm;
    public String end_dttm;
    public int state;


    /**
     * 백테스팅용
     */
    public void BackTesting(){
        try {
            this.end_dttm = "202309030900";
            this.srt_dttm = "202309031200";
            Date to_date_end = new SimpleDateFormat("yyyyMMddHHmm").parse(this.end_dttm);
            Date to_date_srt = new SimpleDateFormat("yyyyMMddHHmm").parse(this.srt_dttm);


            long diffSec = (to_date_end.getTime() - to_date_srt.getTime()) / 1000; // 초 차이
            long time = (long)(diffSec / (60 * 30)); //예시 - 30분봉기준

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

        //1. RSI 계산(지표 계산) -- 실제 계산
        getRsiByMinutes.main(); // RSI 계산

        //2. 조건 검증(지금은 RSI 값이 >30 인 로직을 넣을예정)
        int order = new ValidationOrder().validation();

        //3. 매매 (주문가능조회 -> 주문) --
        // order - 만들어줘 진수야

        //   주문 체결 후 처리 - 가잔고 테이블에 insert
        //4. 손익계산 및 잔고정리 (기간 수익을 알기 위해서) - 업비트 API는 해당 정보가 없다. 직접해야함
        // 개별주문조회 API 정리해줘야 함 체결됬는지 안됐는지
        // API 타고

    }


}
