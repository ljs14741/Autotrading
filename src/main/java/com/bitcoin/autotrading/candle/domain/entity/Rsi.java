package com.bitcoin.autotrading.candle.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "rsi")
@AllArgsConstructor
@Builder
public class Rsi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("candle_date_time_kst")
    private	String	candleDateTimeKst;

    @JsonProperty("rsi")
    private double rsi;

}