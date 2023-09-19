package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.candle.service.GetRsiByDay;
import com.bitcoin.autotrading.coin.domain.CoinPrice;
import com.bitcoin.autotrading.coin.domain.CoinPriceDTO;
import com.bitcoin.autotrading.coin.service.CoinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@Slf4j
public class StartController {

    @Autowired
    public GetRsiByDay getRsiByDay;
    @Autowired
    public CoinInfoService coinInfoService;


    @RequestMapping("/")
    public String index(Model model) throws IOException, JSONException, ParseException {
        coinInfoService.coinInfo();
        List<CoinPrice> list = coinInfoService.coinPriceSelect();
        model.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("/test")
    public String accountInfo(Model model) throws IOException {
        return "test";
    }
}
