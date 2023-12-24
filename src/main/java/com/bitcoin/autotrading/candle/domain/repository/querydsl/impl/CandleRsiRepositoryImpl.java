package com.bitcoin.autotrading.candle.domain.repository.querydsl.impl;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.QCandle;
import com.bitcoin.autotrading.candle.domain.entity.QRsi;
import com.bitcoin.autotrading.candle.domain.repository.querydsl.CandleRsiAbstrRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CandleRsiRepositoryImpl implements CandleRsiAbstrRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CandleRsiRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
    @Override
    public List<CandleDTO> findByCandleRsi() {
        log.info("CandleRsiRepositoryImpl");
        QCandle candle = QCandle.candle;
        QRsi rsi = QRsi.rsi;
        return jpaQueryFactory.select(Projections.fields(CandleDTO.class,
                candle.market.as("market"),
                candle.candleDateTimeKst.as("candleDateTimeKst"),
                candle.openingPrice.as("openingPrice"),
                candle.highPrice.as("highPrice"),
                candle.lowPrice.as("lowPrice"),
                candle.tradePrice.as("tradePrice"),
                rsi.rsiValue.as("rsiValue")))
                .from(candle)
                .join(rsi).on(candle.candleDateTimeKst.eq(rsi.candleDateTimeKst))
                .fetch();
    }
}
