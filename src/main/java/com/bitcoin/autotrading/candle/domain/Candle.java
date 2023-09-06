package com.bitcoin.autotrading.candle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "candle")
@NoArgsConstructor
public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private	String	market;
    private	String	candle_date_time_utc;
    private	String	candle_date_time_kst;
    private	String	opening_price;
    private	Double	high_price;
    private	Double	low_price;
    private	String	trade_price;
    private	Long	timestamp;
    private	Double	candle_acc_trade_price;
    private	Double	candle_acc_trade_volume;
    private	Integer	unit;
    private Double  rsi;


    @Builder
    public Candle(String market,String candle_date_time_kst, String candle_date_time_utc,String opening_price){
        this.market=market;
        this.candle_date_time_kst=candle_date_time_kst;
        this.candle_date_time_utc=candle_date_time_utc;
        this.opening_price=opening_price;
    }



}