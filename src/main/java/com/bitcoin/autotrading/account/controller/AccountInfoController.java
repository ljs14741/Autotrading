package com.bitcoin.autotrading.account.controller;

import com.bitcoin.autotrading.account.service.AccountInfoService;
import com.bitcoin.autotrading.candle.service.GetRsiByDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class AccountInfoController {

    @Autowired
    public AccountInfoService accoutninfoService;

    @RequestMapping("/testAccountInfo")
    public String AccountInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        List<Map<String, Object>> list = accoutninfoService.accountInfo();
        request.setAttribute("list", list);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("testAccountInfo");
        return "testAccountInfo";
    }
}
