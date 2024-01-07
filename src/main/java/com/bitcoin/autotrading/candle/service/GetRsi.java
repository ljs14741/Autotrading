package com.bitcoin.autotrading.candle.service;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.bitcoin.autotrading.common.RequestUpbit;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@Component
public class GetRsi {

    @Autowired
    private RequestUpbit requestUpbit;

    public double getRsi(String dttm, String unit, String market) throws IOException, JSONException, ParseException {

        String url = "https://api.upbit.com/v1/candles/"+unit+"?market="+market+"&to="+dttm+"&count=200";
        String data = requestUpbit.request(url);
        JSONArray jsonArray = new JSONArray(data);
//        List<Map<String, Object>> list = JsonTransfer.getListMapFromJsonArray(jsonArray);
        List<CandleDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CandleDTO>() {
        });

        //rsi 계산
        double rsi = new CalculateRsi().calculateRsi(list);

        return rsi;

    }
}
