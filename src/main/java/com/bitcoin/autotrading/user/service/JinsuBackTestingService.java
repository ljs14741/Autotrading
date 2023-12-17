package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.bitcoin.autotrading.candle.domain.repository.CandleRepository;
import com.bitcoin.autotrading.candle.service.CalculateRsi;
import com.bitcoin.autotrading.candle.service.GetRsi;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.bitcoin.autotrading.common.RequestUpbit;
import com.bitcoin.autotrading.user.domain.entity.UserCondition;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class JinsuBackTestingService {
    @Autowired
    private RequestUpbit requestUpbit;

    @Autowired
    GetRsi getRsi;
    private final CandleRepository candleRepository;
    private String currentDateString;

    public JinsuBackTestingService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    public void JinsuBackTesting(UserCondition userCondition) throws JSONException, IOException, ParseException {
        // 0. 파라미터 받기
        this.currentDateString = userCondition.getSrtDttm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        LocalDateTime curruntDate = userCondition.getSrtDttm();
        log.info("curruntDate : " + curruntDate);

        /*
        // 1. 코인 정보
        String url = "https://api.upbit.com/v1/candles/days?market=KRW-XRP&to=2023-10-27 09:00:00&count=200";
        String data = requestUpbit.request(url);
        JSONArray jsonArray = new JSONArray(data);
        List<CandleDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CandleDTO>() {
        });

        list.forEach(item -> {
            coinVolatilityInsert(item);
        });
         */

        // 2. rsi 값
        double rsi = getRsi.getRsi(currentDateString,"days",userCondition.getMarket());
        log.info("rsi : " + rsi);

        // 3. 1번 2번 조인 및 수익률계산

        // 4. 화면 반환

    }

    @Transactional
    public void coinVolatilityInsert(CandleDTO candleDTO) {
        log.info("insertinsertinsert: " + candleDTO);

        Candle candle = Candle.builder()
                .market(candleDTO.getMarket())
                .candleDateTimeKst(candleDTO.getCandleDateTimeKst())
                .openingPrice(candleDTO.getOpeningPrice())
                .highPrice(candleDTO.getHighPrice())
                .lowPrice(candleDTO.getLowPrice())
                .tradePrice(candleDTO.getTradePrice())
                .build();
        candleRepository.save(candle);
    }
}
