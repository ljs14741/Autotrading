package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.user.service.BackTestingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@Slf4j
public class BackTestingController {

    @Autowired
    BackTestingService fromToTradingService;

    @RequestMapping("/testFromToTrading")
    public String FromtoTrading() throws IOException {
        fromToTradingService.BackTesting();

        return "testFromToTrading";
    }
}
