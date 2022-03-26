package com.bitcoin.autotrading.trading.client.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Builder
@ToString
@Getter
public class CommonResponse<T>{

    public Class<T> response;

}
