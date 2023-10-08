package com.bitcoin.autotrading.candle.service;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.bitcoin.autotrading.common.RequestUpbit;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.io.IOException;
import java.util.List;

@Component
@NoArgsConstructor
@Slf4j
//시세캔틀조회 (분)
public class GetCandle {

    @Autowired
    private RequestUpbit requestUpbit;

    public List<CandleDTO> getCandle(String dttm, String unit, String market, int cnt ) throws InterruptedException, IOException, JSONException {

        String url = "https://api.upbit.com/v1/candles/"+unit+"?market="+market+"&to="+dttm+"&count="+cnt;
        String data = requestUpbit.request(url);
        JSONArray jsonArray = new JSONArray(data);
        List<CandleDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CandleDTO>() {
        });

        return list;

    }
}
