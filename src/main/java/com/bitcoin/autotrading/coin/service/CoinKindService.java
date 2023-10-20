package com.bitcoin.autotrading.coin.service;

import com.bitcoin.autotrading.coin.domain.dto.CoinKindDTO;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CoinKindService {
        public String coinKind = "=";
        public String temp = null;

        public String coinKind() throws IOException, JSONException {

        this.coinKind = "=";
        this.temp = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/market/all?isDetails=false")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();
        JSONArray jsonArray = new JSONArray(data);
        List<CoinKindDTO> CoinKindDTO = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CoinKindDTO>() {});

        CoinKindDTO.forEach(item -> {
                if(item.getMarket().contains("KRW")) {
                        this.temp = item.getMarket();
                        this.coinKind = this.coinKind.concat(this.temp.concat(","));
                }
        });
        this.coinKind = Optional.ofNullable(this.coinKind) // java 8 이상에서 문자열의 마지막 문자 제거 "," // Optional클래스를 이용하여 null 예외방지
                .filter(item -> item.length() !=0)
                .map(item -> item.substring(0, item.length() -1))
                .orElse(this.coinKind);
        return coinKind;
    }
}
