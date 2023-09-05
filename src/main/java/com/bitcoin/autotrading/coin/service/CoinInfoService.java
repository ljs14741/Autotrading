package com.bitcoin.autotrading.coin.service;

import com.bitcoin.autotrading.common.JsonTransfer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CoinInfoService {

    public List<Map<String, Object>> coinInfo() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets=KRW-BTC,KRW-ETH,KRW-XRP")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();

        //     JSONArray 데이터의 'market'이라는 키의 밸류만 뽑아낸것
        JSONArray jsonArray = new JSONArray(data);

        List<Map<String, Object>> list = JsonTransfer.getListMapFromJsonArray(jsonArray);
        return list;
    }

//    public void coinKind() throws IOException, JSONException {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://api.upbit.com/v1/market/all?isDetails=false")
//                .get()
//                .addHeader("accept", "application/json")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        String data = response.body().string();
//        JSONArray jsonArray = new JSONArray(data);
//        List<Map<String, Object>> list = JsonTransfer.getListMapFromJsonArray(jsonArray);
//
//    }
}
