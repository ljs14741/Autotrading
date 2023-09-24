package com.bitcoin.autotrading.candle.domain.dto;

import com.bitcoin.autotrading.candle.domain.Candle;
import com.bitcoin.autotrading.order.domain.dto.OrderDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Field;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class CandleDto {


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

    public static CandleDto toDto(Candle candle){
        return CandleDto.builder()
                .market(candle.getMarket())
                .candle_acc_trade_price(candle.getCandle_acc_trade_price())
                .candle_acc_trade_volume(candle.getCandle_acc_trade_volume())
                .candle_date_time_kst(candle.getCandle_date_time_kst())
                .candle_date_time_utc(candle.getCandle_date_time_utc())
                .high_price(candle.getHigh_price())
                .low_price(candle.getLow_price())
                .opening_price(candle.getOpening_price())
                .timestamp(candle.getTimestamp())
                .trade_price(candle.getTrade_price())
                .unit(candle.getUnit())
                .rsi(candle.getRsi())
                .build();
    }
}
