package com.bitcoin.autotrading.trading.client;

import com.bitcoin.autotrading.common.client.RequestMethod;
import com.bitcoin.autotrading.common.client.config.MakeHeader;
import com.bitcoin.autotrading.trading.client.model.dto.AccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UpbitClient{

    private final RequestMethod httpMethod;
    private final MakeHeader header;

    @GetMapping(path = "/v1/accounts")
    public void getAccountList() throws Exception {
        HttpHeaders httpHeaders = header.make(null);
        AccountResponse accountResponse = httpMethod.get("/v1/accounts",null, new ParameterizedTypeReference<>(){
                },httpHeaders);

        log.debug(accountResponse.toString());
    }

}
