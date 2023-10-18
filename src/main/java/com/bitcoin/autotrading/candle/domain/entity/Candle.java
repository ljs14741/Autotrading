package com.bitcoin.autotrading.candle.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "candle")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private long id;

    @JsonProperty("market")
    @Column(name = "market")
    private	String	market;

    @JsonProperty("candle_date_time_utc")
    private	String	candleDateTimeUtc;

    @JsonProperty("candle_date_time_kst")
    private	String	candleDateTimeKst;

    @JsonProperty("opening_price")
    private	Double	openingPrice;

    @JsonProperty("high_price")
    private	Double	highPrice;

    @JsonProperty("low_price")
    private	Double	lowPrice;

    @JsonProperty("trade_price")
    private	Double	tradePrice;

    @JsonProperty("timestamp")
    private	Long	timestamp;

    @JsonProperty("candle_acc_trade_price")
    private	Double	candleAccTradePrice;

    @JsonProperty("candle_acc_trade_volume")
    private	Double	candleAccTradeVolume;

    @JsonProperty("unit")
    private	Integer	unit;

    @JsonProperty("rsi")
    private Double  rsi;

//    @JsonProperty("range")
//    private Double range;
//
//    @JsonProperty("target")
//    private Double target;
//
//    @JsonProperty("earnings")
//    private Double earnings;
}