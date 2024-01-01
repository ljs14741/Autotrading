package com.bitcoin.autotrading.account.controller;

import com.bitcoin.autotrading.account.Repository.AccountRepository;
import com.bitcoin.autotrading.account.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
public class AccountInfoController {

    private final AccountInfoService accoutninfoService;

    public AccountInfoController(AccountInfoService accoutninfoService) {
        this.accoutninfoService = accoutninfoService;
    }

    @RequestMapping("/testAccountInfo")
    public String accountInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, Object>> list = accoutninfoService.accountInfo();
        request.setAttribute("list", list);
        return "testAccountInfo";
    }
}
