package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.user.domain.entity.UserCondition;
import com.bitcoin.autotrading.user.service.JinsuBackTestingService;
import com.bitcoin.autotrading.user.service.VolatilityBackTestingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Controller
@Slf4j
public class JinsuBackTestingController {

    @Autowired
    private JinsuBackTestingService jinsuBackTestingService;
    @RequestMapping("/jinsuBackTestingController.jinsuBackTesting.do")
    public void jinsuBackTesting(@RequestBody UserCondition userCondition) throws IOException, JSONException, InterruptedException, ParseException {
        log.info("진수백테스팅 테스드");
        log.info("request userCondition: " + userCondition);
        jinsuBackTestingService.JinsuBackTesting(userCondition);
    }
}
