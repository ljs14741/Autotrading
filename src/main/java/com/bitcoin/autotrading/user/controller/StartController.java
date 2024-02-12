package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.account.domain.entity.Account;
import com.bitcoin.autotrading.account.service.AccountInfoService;
import com.bitcoin.autotrading.candle.service.CalculateRsi;
import com.bitcoin.autotrading.coin.domain.entity.CoinPrice;
import com.bitcoin.autotrading.coin.service.CoinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
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

//    @Autowired
//    public CalculateRsi calculateRsi;
    @Autowired
    public CoinInfoService coinInfoService;
    @Autowired
    public AccountInfoService accountInfoService;

    @RequestMapping("/")
    public String index(Model model) throws IOException, JSONException, ParseException {
        /* 2024-02-12 계좌관리 임시주석 AWS EC2 서버 띄우려고*/
//        accountInfoService.accountInfoSave();
//        List<Account> accountList = accountInfoService.accountSelect();
//        int balance = (int)Math.floor(accountList.get(0).getBalance());
//        model.addAttribute("balance",balance);

        coinInfoService.coinInfoSave();
        List<CoinPrice> list = coinInfoService.coinPriceAllSelect();
        model.addAttribute("list",list);
        return "index";
    }


    @RequestMapping("/test")
    public String accountInfo(Model model) throws IOException {
        return "test";
    }
}
