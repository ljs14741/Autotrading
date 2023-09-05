package com.bitcoin.autotrading.user.domain;

import com.bitcoin.autotrading.account.domain.Account;
import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.order.domain.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseBackTestingDTO {
    public Account account;
    public List<Order> orderList;
    public List<Candle> candleList;

    @Builder
    public ResponseBackTestingDTO(Account account, List<Order> orderList, List<Candle> candleList){
        this.account=account;
        this.candleList=candleList;
        this.orderList=orderList;
    }


}
