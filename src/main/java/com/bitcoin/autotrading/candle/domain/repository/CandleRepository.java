package com.bitcoin.autotrading.candle.domain.repository;

import com.bitcoin.autotrading.candle.domain.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandleRepository extends JpaRepository<Candle, String> {
    // 일반 SQL쿼리
    @Query(value =
            "select id" +
                ", candle_date_time_kst" +
                ", opening_price" +
                ", high_price" +
                ", low_price" +
                ", trade_price" +
                ", next_high_price" +
                ", next_low_price" +
                ", range" +
                ", target" +
                ", case when high_price > target then trade_price/target else '0' end as earnings" +
                ", candle_acc_trade_volume" +
                ", market" +
                ", candle_date_time_utc" +
                ", candle_acc_trade_price" +
                ", unit" +
                ", timestamp" +
                ", rsi " +
                "from (select id" +
                            ", candle_date_time_kst" +
                            ", opening_price" +
                            ", high_price" +
                            ", low_price" +
                            ", trade_price" +
                            ", next_high_price" +
                            ", next_low_price" +
                            ", round((next_high_price - next_low_price) * 0.5,0) as range" +
                            ", round(opening_price + (next_high_price - next_low_price) * 0.5,0) as target" +
                            ", candle_acc_trade_volume" +
                            ", market" +
                            ", candle_date_time_utc" +
                            ", candle_acc_trade_price" +
                            ", unit" +
                            ", timestamp" +
                            ", rsi " +
                            "from (select id" +
                                        ", candle_date_time_kst" +
                                        ", opening_price" +
                                        ", high_price" +
                                        ", low_price" +
                                        ", trade_price" +
                                        ", lag(high_price) over(order by id) as next_high_price" +
                                        ", lag(low_price) over(order by id) as next_low_price" +
                                        ", candle_acc_trade_volume" +
                                        ", market" +
                                        ", candle_date_time_utc" +
                                        ", candle_acc_trade_price" +
                                        ", unit" +
                                        ", timestamp" +
                                        ", rsi " +
                                        "from candle " +
                                        "where 1=1) as x) as y", nativeQuery = true)
    public List<Candle> selectSQL();
}
