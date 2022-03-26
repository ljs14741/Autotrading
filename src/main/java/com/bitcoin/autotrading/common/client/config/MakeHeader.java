package com.bitcoin.autotrading.common.client.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@PropertySource("classpath:key.properties")
@Slf4j
@Component
public class MakeHeader {

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${serverUrl}")
    private String serverUrl;

    public HttpHeaders make(HashMap<String,String> params) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String authenticationToken;
        String jwtToken;
        String queryString="";

        if(params.isEmpty()){
            jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .sign(algorithm);

            authenticationToken = "Bearer " + jwtToken;

        }else{

            ArrayList<String> queryElements = new ArrayList<>();
            for(Map.Entry<String, String> entity : params.entrySet()) {
                queryElements.add(entity.getKey() + "=" + entity.getValue());
            }

            queryString = String.join("&", queryElements.toArray(new String[0]));

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(queryString.getBytes("UTF-8"));

            String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

            log.info("queryHash   =["+ queryHash+"]");
            log.info("queryString =["+ queryString+"]");

            jwtToken = JWT.create()
                    .withClaim("access_key", accessKey)
                    .withClaim("nonce", UUID.randomUUID().toString())
                    .withClaim("query_hash", queryHash)
                    .withClaim("query_hash_alg", "SHA512")
                    .sign(algorithm);

            authenticationToken = "Bearer " + jwtToken;
        }

        headers.add("Authorization", authenticationToken);

        return headers;
    }

}
