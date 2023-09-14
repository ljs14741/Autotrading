package com.bitcoin.autotrading.coin.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j

public class CoinPriceDTO {
    private Long id;
    private String market;
    private String trade_date;
    private String trade_time;
    private String trade_date_kst;
    private String trade_time_kst;
    private Long trade_timestamp;
    private Double opening_price;
    private Double high_price;
    private Double low_price;
    private Double trade_price;
    private Double prev_closing_price;
    private String change;
    private Double change_price;
    private Double change_rate;
    private Double signed_change_price;
    private Double signed_change_rate;
    private Double trade_volume;
    private Double acc_trade_price;
    private Double acc_trade_price_24h;
    private Double acc_trade_volume;
    private Double acc_trade_volume_24h;
    private Double highest_52_week_price;
    private String highest_52_week_date;
    private Double lowest_52_week_price;
    private String lowest_52_week_date;
    private Long timestamp;

    @Builder
    public CoinPriceDTO(String market, Double opening_price, Double high_price, Double low_price, Double trade_price, String change) {
        this.market = market;
        this.opening_price = opening_price;
        this.high_price = high_price;
        this.low_price = low_price;
        this.trade_price = trade_price;
        this.change = change;
    }
}
