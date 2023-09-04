package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.user.service.BackTestingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class BackTestingController {

    @Autowired
    BackTestingService backTestingService;

    @RequestMapping("/testFromToTrading")
    public String fromToTrading(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, JSONException, ParseException {
        List<Map<String, Object>> list = backTestingService.backTesting();
        request.setAttribute("list", list);
        return "testFromToTrading";
    }
}
