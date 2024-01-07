package com.bitcoin.autotrading.account.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bitcoin.autotrading.account.Repository.AccountRepository;
import com.bitcoin.autotrading.account.domain.entity.Account;
import com.bitcoin.autotrading.account.domain.dto.AccountDTO;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
// 전체계좌조회
public class AccountInfoService {
    private final AccountRepository accountRepository;

    public AccountInfoService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //@Scheduled(fixedDelay = 1000000) //일정시간마다 아래 함수 실행하는 스케쥴러 (1000 -> 1초)
    public List<Map<String, Object>> accountInfo() throws JSONException {
        String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
        String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
        String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        String body = "";
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(body);
        List<Map<String, Object>> list = JsonTransfer.getListMapFromJsonArray(jsonArray);
        return list;
    }

    public void accountInfoSave() throws JSONException {

        String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
        String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
        String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        String body = "";
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(body);
        List<AccountDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<AccountDTO>() {});

        list.forEach(item -> {
            accountInsert(item);
        });
    }

    @Transactional
    public void accountInsert(AccountDTO accountDto) {
        log.info("insertinsertinsert: " + accountDto);

        Account account = Account.builder()
                .currency(accountDto.getCurrency())
                .balance(accountDto.getBalance())
                .locked(accountDto.getLocked())
                .avgBuyPrice(accountDto.getAvgBuyPrice())
                .avgBuyPriceModified(accountDto.getAvgBuyPriceModified())
                .unitCurrency(accountDto.getUnitCurrency())
                .build();
        accountRepository.save(account);
    }

    public List<Account> accountSelect() {
        String currency = "KRW";
        return accountRepository.findAllByCurrency(currency);
    }
}
