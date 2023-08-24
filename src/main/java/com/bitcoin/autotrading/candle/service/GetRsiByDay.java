package com.bitcoin.autotrading.candle.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class GetRsiByDay {

    public double GetRsiBy() throws IOException, JSONException {

        log.info("getRsiByDay 타냐?");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                //.url("https://api.upbit.com/v1/candles/minutes/240?market=KRW-BTC&count=14") // miniue 오른쪽 숫자는 몇분봉 , 맨오른쪽 숫자는 출력개수
                 .url("https://api.upbit.com/v1/candles/days?market=KRW-BTC&count=200")
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
        Collections.reverse(list);
        log.info("daylist -> " +  list);

//        ------------- rsi 계산 //   https://herojoon-dev.tistory.com/156
        double zero = 0;
        List<Double> upList = new ArrayList<>();
        List<Double> downList = new ArrayList<>();

        List<String> dou2_dou1List = new ArrayList<String>();
        List<String> upupList = new ArrayList<String>();
        List<String> downdownList = new ArrayList<String>();

        for(int i=0; i < list.size()-1; i++) {
            String str2 = list.get(i+1).get("trade_price").toString();
            String str1 = list.get(i).get("trade_price").toString();
            double dou2 = Double.parseDouble(str2);
            double dou1 = Double.parseDouble(str1);
            double gapByTradePrice = dou2-dou1;

            /////
            dou2_dou1List.add(String.valueOf(gapByTradePrice));

            /////
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
        //double a = 2.0 / (day + 1);

        //AU
        double upEma = 0;
        if(!CollectionUtils.isEmpty(upList)) {
            upEma = upList.get(0).doubleValue();
            if(upList.size() > 1) {
                for(int i=1; i<upList.size(); i++) {
                    upEma = (upList.get(i).doubleValue() * a) + (upEma * (1-a));

                    upupList.add(String.valueOf(upEma));
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

                    downdownList.add(String.valueOf(downEma));
                }
            }
        }

        // rsi 계산
        double au = upEma;
        double ad = downEma;
        double rs = au / ad;
        double rsi = 100 - (100 / (1 + rs));


        log.info("Day rsi -> " + rsi);
//        model.addAttribute("au",au);
//        model.addAttribute("ad",ad);
//        model.addAttribute("rsi",rsi);
//        model.addAttribute("upList",upList);
//        model.addAttribute("downList",downList);
//        model.addAttribute("dou2_dou1List",dou2_dou1List);
//        model.addAttribute("upupList",upupList);
//        model.addAttribute("downdownList",downdownList);
        return rsi;
    }
}
