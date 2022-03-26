package com.bitcoin.autotrading.trading.client.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
@Builder
public class AccountResponse{

        @JsonProperty("currency")
        public String currency;

        @JsonProperty("balance")
        public double balance;

        @JsonProperty("locked")
        public double locked;

        @JsonProperty("avg_buy_price")
        public double avg_buy_price;

        @JsonProperty("avg_buy_price_modified")
        public boolean avg_buy_price_modified;

        @JsonProperty("unit_currency")
        public String unit_currency;

}
