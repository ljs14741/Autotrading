package com.bitcoin.autotrading.account.domain.dto;

import com.bitcoin.autotrading.account.domain.Account;
import com.bitcoin.autotrading.candle.domain.dto.CandleDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * DTO for {@link com.bitcoin.autotrading.account.domain.Account}
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class AccountDto{
    private int id;
    private String currency;
    private Double balance;
    private Double locked;
    private Double avg_buy_price;
    private Boolean avg_buy_price_modified;
    private String unit_currency;

    public static AccountDto toDto(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .locked(account.getLocked())
                .avg_buy_price(account.getAvg_buy_price())
                .avg_buy_price_modified(account.getAvg_buy_price_modified())
                .unit_currency(account.getUnit_currency())
                .build();
    }

}