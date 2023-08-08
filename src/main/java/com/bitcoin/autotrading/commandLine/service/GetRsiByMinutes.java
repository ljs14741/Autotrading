package com.bitcoin.autotrading.commandLine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class GetRsiByMinutes {

    public static void main() throws InterruptedException, IOException, JSONException {
        System.out.println("candle main");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/candles/minutes/240?market=KRW-BTC&count=14") // miniue 오른쪽 숫자는 몇분봉 , 맨오른쪽 숫자는 출력개수
                // .url("https://api.upbit.com/v1/candles/days?count=1")
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();

   //     JSONArray 데이터의 'market'이라는 키의 밸류만 뽑아낸것
        JSONArray jsonArray = new JSONArray(data);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String trade_price = jsonObject.getString("trade_price");
//            log.info("trade_price -> " + trade_price);
//        }
        //GetRsiByMinutes.getListMapFromJsonArray(jsonArray);
        List<Map<String, Object>> list = GetRsiByMinutes.getListMapFromJsonArray(jsonArray);
        log.info("list -> " +  list);

//        ------------- rsi 계산 //   https://herojoon-dev.tistory.com/156
        double zero = 0;
        List<Double> upList = new ArrayList<>();
        List<Double> downList = new ArrayList<>();
        for(int i=0; i<list.size()-1; i++) {
            String str2 = list.get(i+1).get("trade_price").toString();
            String str1 = list.get(i).get("trade_price").toString();
            double dou2 = Double.parseDouble(str2);
            double dou1 = Double.parseDouble(str1);
            double gapByTradePrice = dou2-dou1;
            if(gapByTradePrice > 0) {
                upList.add(gapByTradePrice);
                downList.add(zero);
            } else if(gapByTradePrice < 0) {
                downList.add(gapByTradePrice * -1);
                upList.add(zero);
            } else {
                upList.add(zero);
                downList.add(zero);
            }
        }

        double day = 14;
        double a = (double) 1 / (1 + (day-1));

        //AU
        double upEma = 0;
        if(!CollectionUtils.isEmpty(upList)) {
           upEma = upList.get(0).doubleValue();
           if(upList.size() > 1) {
               for(int i=0; i<upList.size(); i++) {
                   upEma = (upList.get(i).doubleValue() * a) + (upEma * (1-a));
               }
           }
        }

        // AD
        double downEma = 0;  // 하락 값의 지수이동평균
        if(!CollectionUtils.isEmpty(downList)) {
            downEma = downList.get(0).doubleValue();
            if(downList.size() > 1) {
                for(int i=1; i<downList.size(); i++) {
                    downEma = (downList.get(i).doubleValue() * a) + (downEma * (1 - a));
                }
            }
        }

        // rsi 계산
        double au = upEma;
        double ad = downEma;
        double rs = au / ad;
        double rsi = 100 - (100 / (1 + rs));

        log.info("rsi -> " + rsi);
    }

    
    // 아래 두개 함수는 json -> list로 변경하려고 해본건데 되긴되는거 같은데 잘모르겠네
    public static Map<String, Object> getMapFromJSONObject(JSONObject obj) {
        if (ObjectUtils.isEmpty(obj)) {
            log.error("BAD REQUEST obj : {}", obj);
            throw new IllegalArgumentException(String.format("BAD REQUEST obj %s", obj));
        }

        try {
            return new ObjectMapper().readValue(obj.toString(), Map.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) throws JSONException {

        if (ObjectUtils.isEmpty(jsonArray)) {
            log.error("jsonArray is null.");
            throw new IllegalArgumentException("jsonArray is null");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++) {
            Map<String, Object> map = getMapFromJSONObject((JSONObject)jsonArray.get(i));
            list.add(map);
           // list.add(getMapFromJSONObject((JSONObject) jsonObject));
        }
        return list;
    }
}


