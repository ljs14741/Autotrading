package com.bitcoin.autotrading.user.domain;

import com.bitcoin.autotrading.account.domain.dto.AccountDTO;
import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.order.domain.dto.OrderDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class ResponseBackTestingDto {
    public AccountDTO account;
    public List<OrderDto> orderList;
    public List<CandleDTO> candleList;

    @Builder
    public ResponseBackTestingDto(AccountDTO account, List<OrderDto> orderList, List<CandleDTO> candleList){
        this.account=account;
        this.candleList=candleList;
        this.orderList=orderList;
    }

}
