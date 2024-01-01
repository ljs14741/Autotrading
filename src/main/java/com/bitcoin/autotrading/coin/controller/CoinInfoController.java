package com.bitcoin.autotrading.coin.controller;

import com.bitcoin.autotrading.coin.domain.entity.CoinPrice;
import com.bitcoin.autotrading.coin.service.CoinInfoService;
import com.bitcoin.autotrading.coin.service.CoinKindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoinInfoController {

    @Autowired
    public CoinInfoService coinInfoService;
    @Autowired
    public CoinKindService coinKindService;

    @RequestMapping("/testCoinInfo")
    public String coinInfoInsert(Model model) throws Exception {
        coinInfoService.coinInfoSave();
        List<CoinPrice> list = coinInfoService.coinPriceAllSelect();
        model.addAttribute("list",list);
        return "testCoinInfo";
    }

    @RequestMapping("/coinInfoController.coinPriceSelect.do")
    @ResponseBody
    public List<CoinPrice> coinPriceSelect(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String requestParam = request.getParameter("requestParam");
        List<CoinPrice> responseParam = coinInfoService.coinPriceSelect(requestParam);
        log.info("haha: " + responseParam);
        return responseParam;
    }
}

