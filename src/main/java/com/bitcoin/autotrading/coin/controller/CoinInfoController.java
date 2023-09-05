package com.bitcoin.autotrading.coin.controller;

import com.bitcoin.autotrading.coin.service.CoinInfoService;
import com.bitcoin.autotrading.order.service.GetOrdersChance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Controller
public class CoinInfoController {

    @Autowired
    public CoinInfoService coinInfoService;

    @RequestMapping("/testCoinInfo")
    public String coinInfo(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchAlgorithmException {
        List<Map<String, Object>> list = coinInfoService.coinInfo();
        request.setAttribute("list", list);

//        coinInfoService.coinKind();
        return "testCoinInfo";
    }
}
