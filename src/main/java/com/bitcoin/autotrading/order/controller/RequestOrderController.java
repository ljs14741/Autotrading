package com.bitcoin.autotrading.order.controller;

import com.bitcoin.autotrading.order.service.RequestOrderService;
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
public class RequestOrderController {


    @Autowired
    private RequestOrderService requestOrderService;

    @RequestMapping("/testRequestOrder.do")
    public String AccountInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, NoSuchAlgorithmException {


        requestOrderService.main();
        return "testRequestOrder";
    }
}
