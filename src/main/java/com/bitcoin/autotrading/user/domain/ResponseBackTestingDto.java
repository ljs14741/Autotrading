package com.bitcoin.autotrading.user.domain;

import com.bitcoin.autotrading.account.domain.Account;
import com.bitcoin.autotrading.account.domain.dto.AccountDto;
import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.candle.domain.dto.CandleDto;
import com.bitcoin.autotrading.order.domain.Order;
import com.bitcoin.autotrading.order.domain.dto.OrderDto;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class ResponseBackTestingDto {
    public AccountDto account;
    public List<OrderDto> orderList;
    public List<CandleDto> candleList;

    @Builder
    public ResponseBackTestingDto(AccountDto account, List<OrderDto> orderList, List<CandleDto> candleList){
        this.account=account;
        this.candleList=candleList;
        this.orderList=orderList;
    }

}
