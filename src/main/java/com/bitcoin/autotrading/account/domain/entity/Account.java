package com.bitcoin.autotrading.account.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "account")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
}