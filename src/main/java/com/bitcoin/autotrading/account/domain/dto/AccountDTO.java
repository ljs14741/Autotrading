package com.bitcoin.autotrading.account.domain.dto;

import com.bitcoin.autotrading.account.domain.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * DTO for {@link Account}
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class AccountDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("locked")
    private Double locked;

    @JsonProperty("avg_buy_price")
    private Double avgBuyPrice;

    @JsonProperty("avg_buy_price_modified")
    private Boolean avgBuyPriceModified;

    @JsonProperty("unit_currency")
    private String unitCurrency;

    public static AccountDTO toDto(Account account){
        return AccountDTO.builder()
                .id(account.getId())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .locked(account.getLocked())
                .avgBuyPrice(account.getAvgBuyPrice())
                .avgBuyPriceModified(account.getAvgBuyPriceModified())
                .unitCurrency(account.getUnitCurrency())
                .build();
    }

}