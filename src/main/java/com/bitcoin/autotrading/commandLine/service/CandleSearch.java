package com.bitcoin.autotrading.commandLine.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

@Component
@NoArgsConstructor
@Slf4j
//시세캔틀조회 (분)
public class CandleSearch {

    public void main() throws InterruptedException, IOException {
        System.out.println("candle main");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=14")
                // .url("https://api.upbit.com/v1/candles/days?count=1")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        log.info("시세캔틀조회 -> " + response.body().string());

    }
}
