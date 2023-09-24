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
    private	String	candle_date_time_utc;
    private	String	candle_date_time_kst;
    private	Double	opening_price;
    private	Double	high_price;
    private	Double	low_price;
    private	Double	trade_price;
    private	Long	timestamp;
    private	Double	candle_acc_trade_price;
    private	Double	candle_acc_trade_volume;
    private	Integer	unit;
    private Double  rsi;

}