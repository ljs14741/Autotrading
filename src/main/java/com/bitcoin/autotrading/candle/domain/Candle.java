package com.bitcoin.autotrading.candle.domain;

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
    private long id;

    private	String	market;
    private	String	candleDateTimeUtc;
    private	String	candleDateTimeKst;
    private	Double	openingPrice;
    private	Double	highPrice;
    private	Double	lowPrice;
    private	Double	tradePrice;
    private	Long	timeStamp;
    private	Double	candleAccTradePrice;
    private	Double	candleAccTradeVolume;
    private	Integer	unit;
    private Double  rsi;

}