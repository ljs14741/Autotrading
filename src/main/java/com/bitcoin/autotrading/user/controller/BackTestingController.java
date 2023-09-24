package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.user.domain.ResponseBackTestingDto;
import com.bitcoin.autotrading.user.service.BackTestingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
@Slf4j
public class BackTestingController {

    @Autowired
    BackTestingService backTestingService;

    @RequestMapping("/testFromToTrading")
    public String fromToTradingView(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, JSONException, ParseException, IllegalAccessException {

        return "testFromToTrading";
    }

    @RequestMapping("/testFromToTrading/trading")
    @ResponseBody
    public ResponseBackTestingDto fromToTrading(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, JSONException, ParseException, IllegalAccessException {
        ResponseBackTestingDto list = backTestingService.backTesting();

        return list;
    }


}
