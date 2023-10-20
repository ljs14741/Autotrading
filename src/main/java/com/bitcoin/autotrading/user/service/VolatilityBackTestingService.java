package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.bitcoin.autotrading.candle.domain.repository.CandleRepository;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.bitcoin.autotrading.common.RequestUpbit;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VolatilityBackTestingService {
    @Autowired
    private RequestUpbit requestUpbit;
    private final CandleRepository candleRepository;

    public VolatilityBackTestingService(CandleRepository candleRepository) {
        this.candleRepository = candleRepository;
    }

    public List<CandleDTO> volatilityBackTesting() throws IOException, JSONException, InterruptedException {
        String url = "https://api.upbit.com/v1/candles/days?market=KRW-XRP&to=2021-05-30 09:00:00&count=200";
        String data = requestUpbit.request(url);
        JSONArray jsonArray = new JSONArray(data);
        List<CandleDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CandleDTO>() {
        });


        list.forEach(item -> {
            coinVolatilityInsert(item);
        });

        List<CandleDTO.CandleProjection> can = candleRepository.selectSQL();
        List<CandleDTO> result;
        can.stream().map(t -> new CandleDTO
                        (t.getMarket(),
                                t.getCandleDateTimeUtc(),
                                t.getCandleDateTimeKst(),
                                t.getOpeningPrice(),
                                t.getHighPrice(),
                                t.getLowPrice(),
                                t.getTradePrice(),
                                t.getTimestamp(),
                                t.getCandleAccTradePrice(),
                                t.getCandleAccTradeVolume(),
                                t.getUnit(),
                                t.getRsi(),
                                t.getRange(),
                                t.getTarget(),
                                t.getEarnings()
        )).collect(Collectors.toList());
        log.info("543543: " + can);
//        List<CandleDTO.CandleProjection> can = candleRepository.selectSQL();
//        log.info("cancan" + can);


        return list;
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
