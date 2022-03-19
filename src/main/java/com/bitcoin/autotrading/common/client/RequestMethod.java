package com.bitcoin.autotrading.common.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class RequestMethod {

    private final WebClient webClient;


    public <T> T get(String path, MultiValueMap<String,String> params, Class<T> clazz, HttpHeaders headers){
        return responseHandle(
                () -> webClient.get()
                        .uri(uriBuilder -> uriBuilder.path(path).queryParams(params).build())
                        .headers(headers_ -> headers_.addAll(headers))
                        .retrieve()
                        .bodyToMono(clazz)
                        .block()
        );
    }

    public <T> T post(String path, Object body, Class<T> clazz, HttpHeaders headers){
        return responseHandle(
                () -> webClient.post()
                        .uri(path)
                        .headers(headers_ -> headers_.addAll(headers))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(clazz)
                        .block()
        );
    }

    public <T> T delete(String path, MultiValueMap<String,String> params, Class<T> clazz, HttpHeaders headers){
        return responseHandle(
                () -> webClient.delete()
                        .uri(uriBuilder -> uriBuilder.path(path).queryParams(params).build())
                        .headers(headers_ -> headers_.addAll(headers))
                        .retrieve()
                        .bodyToMono(clazz)
                        .block()
        );
    }

    private <T> T responseHandle(Supplier<T> request){
        try {
            return request.get();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

}
