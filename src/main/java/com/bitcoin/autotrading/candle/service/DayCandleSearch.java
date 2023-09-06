package com.bitcoin.autotrading.candle.service;

import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@Slf4j
//시세캔틀조회 (분)
public class DayCandleSearch {

    public List<Candle> dayCandleSearch(String srt_dttm) throws InterruptedException, IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/candles/days?market=KRW-BTC&to=" + srt_dttm + "&count=200")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();
        JSONArray jsonArray = new JSONArray(data);
//        List<Map<String, Object>> list = JsonTransfer.getListMapFromJsonArray(jsonArray);
        List<Candle> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<Candle>() {
        });
        log.info(list.get(0).getOpening_price().toString());
        return list;

    }
}
