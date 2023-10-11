package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.bitcoin.autotrading.candle.service.GetCandle;
import com.bitcoin.autotrading.user.service.VolatilityBackTestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class VolatilityBackTestingController {

    @Autowired
    public GetCandle getCandle;
    @Autowired
    private VolatilityBackTestingService volatilityBackTestingService;
    @RequestMapping("/volatilityBackTestingController.volatilityBackTesting.do")
    @ResponseBody
    public List<Candle> volatilityBackTesting(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, InterruptedException {
//        String dttm = "2023-10-04 09:00:00";
//        String unit = "minutes/240";
//        String market = "KRW-XRP";
//        int cnt = 5;
//        getCandle.getCandle(dttm, unit, market, cnt);
        List<Candle> responseParam = volatilityBackTestingService.volatilityBackTesting();
        log.info("111222" + responseParam);
        return responseParam;
    }
}