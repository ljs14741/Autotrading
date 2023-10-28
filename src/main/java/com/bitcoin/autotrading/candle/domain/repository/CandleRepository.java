package com.bitcoin.autotrading.candle.domain.repository;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, String> {
    // 일반 SQL쿼리
    @Query(value =
            "select id" +
                ", candle_date_time_kst as candleDateTimeKst" +
                ", opening_price as openingPrice" +
                ", high_price as highPrice" +
                ", low_price as lowPrice" +
                ", trade_price as tradePrice" +
                ", next_high_price as nextHighPrice" +
                ", next_low_price as nextLowPrice" +
                ", range as range" +
                ", target as target" +
                ", case when high_price > target then trade_price/target else '0' end as earnings" +
                ", candle_acc_trade_volume as candleAccTradeVolume" +
                ", market as market" +
                ", candle_date_time_utc as candleDateTimeUtc" +
                ", candle_acc_trade_price as candleAccTradePrice" +
                ", unit as unit " +
//                ", timestamp as timestamp " +
                "FROM (SELECT id" +
                            ", candle_date_time_kst" +
                            ", opening_price" +
                            ", high_price" +
                            ", low_price" +
                            ", trade_price" +
                            ", next_high_price" +
                            ", next_low_price" +
                            ", round((next_high_price - next_low_price) * 0.5,0) AS range " +
                            ", round(opening_price + (next_high_price - next_low_price) * 0.5,0) AS target" +
                            ", candle_acc_trade_volume" +
                            ", market" +
                            ", candle_date_time_utc" +
                            ", candle_acc_trade_price" +
                            ", unit " +
//                            ", timestamp " +
                            "from (select id" +
                                        ", candle_date_time_kst" +
                                        ", opening_price AS opening_price" +
                                        ", high_price AS high_price" +
                                        ", low_price" +
                                        ", trade_price" +
                                        ", lag(high_price) over(order by id) AS next_high_price" +
                                        ", lag(low_price) over(order by id) as next_low_price " +
                                        ", candle_acc_trade_volume" +
                                        ", market" +
                                        ", candle_date_time_utc" +
                                        ", candle_acc_trade_price" +
                                        ", unit " +
//                                        ", timestamp " +
                                        "from candle " +
                                        "where 1=1) as x) as y", nativeQuery = true)
    public List<CandleDTO.CandleProjection> selectSQL();
}
