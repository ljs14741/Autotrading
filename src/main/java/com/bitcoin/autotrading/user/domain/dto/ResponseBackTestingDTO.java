package com.bitcoin.autotrading.user.domain.dto;

import com.bitcoin.autotrading.account.domain.dto.AccountDTO;
import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.order.domain.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class ResponseBackTestingDTO {

    @JsonProperty("account")
    public AccountDTO account;

    @JsonProperty("order_list")
    public List<OrderDTO> orderList;

    @JsonProperty("candle_list")
    public List<CandleDTO> candleList;

}
