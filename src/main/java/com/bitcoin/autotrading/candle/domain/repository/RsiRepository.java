package com.bitcoin.autotrading.candle.domain.repository;

import com.bitcoin.autotrading.candle.domain.entity.Rsi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsiRepository extends JpaRepository<Rsi, Integer> {
}