package com.bitcoin.autotrading.coin.controller;

import com.bitcoin.autotrading.coin.domain.CoinPrice;
import com.bitcoin.autotrading.coin.domain.CoinPriceDTO;
import com.bitcoin.autotrading.coin.repository.CoinPriceRepository;
import com.bitcoin.autotrading.coin.service.CoinInfoService;
import com.bitcoin.autotrading.coin.service.CoinKindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoinInfoController {

    @Autowired
    public CoinInfoService coinInfoService;
    @Autowired
    public CoinKindService coinKindService;
    private final CoinPriceRepository coinKindRepository;

//    @RequestMapping("/testCoinInfo")
//    public String coinInfo(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, NoSuchAlgorithmException {
//        List<CoinPriceDTO> list = coinInfoService.coinInfo();
//        request.setAttribute("list", list);
//        return "testCoinInfo";
//    }

    @RequestMapping("/testCoinInfo")
    public String coinInfo(Model model) throws JSONException, IOException, NoSuchAlgorithmException {
        List<CoinPriceDTO> list = coinInfoService.coinInfo();
//        model.addAttribute("list",list);

        List<CoinPrice> list2 = coinInfoService.coinPriceSelect();
        model.addAttribute("list2",list2);
        return "testCoinInfo";
    }

    // 전체조회
//    @GetMapping("search")
//    public List<CoinKindEntity> searchAll() {
//        return coinInfoService.searchAll();
//    }
//
//    @GetMapping("insert")
//    public String insertCoinKind(@RequestParam(value = "market") String market
//                               , @RequestParam(value = "korean_name") String korean_name
//                               , @RequestParam(value = "english_name") String english_name
//                               , @RequestParam(value = "market_warning") String market_warning) {
//        return coinInfoService.insertCoinKind(market,korean_name,english_name,market_warning);
//    }
}

