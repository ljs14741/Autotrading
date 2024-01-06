package com.bitcoin.autotrading.candle.domain.repository.querydsl;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;

import java.util.List;

public interface CandleRsiAbstrRepository {
    public List<CandleDTO> findByCandleRsi();
}
