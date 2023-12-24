package com.bitcoin.autotrading.candle.domain.repository;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.bitcoin.autotrading.candle.domain.repository.querydsl.CandleRsiAbstrRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandleRsiRepository extends JpaRepository<Candle, String>, CandleRsiAbstrRepository{
    public List<CandleDTO> findByCandleRsi();
}
