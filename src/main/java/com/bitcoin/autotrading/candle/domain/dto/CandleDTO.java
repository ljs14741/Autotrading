package com.bitcoin.autotrading.candle.domain.dto;

import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class CandleDTO {

    @JsonProperty("market")
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

    public static CandleDTO toDto(Candle candle){
        return CandleDTO.builder()
                .market(candle.getMarket())
                .candleAccTradePrice(candle.getCandleAccTradePrice())
                .candleAccTradeVolume(candle.getCandleAccTradeVolume())
                .candleDateTimeKst(candle.getCandleDateTimeKst())
                .candleDateTimeUtc(candle.getCandleDateTimeUtc())
                .highPrice(candle.getHighPrice())
                .lowPrice(candle.getLowPrice())
                .openingPrice(candle.getOpeningPrice())
                .timestamp(candle.getTimestamp())
                .tradePrice(candle.getTradePrice())
                .unit(candle.getUnit())
                .rsi(candle.getRsi())
                .build();
    }
}
