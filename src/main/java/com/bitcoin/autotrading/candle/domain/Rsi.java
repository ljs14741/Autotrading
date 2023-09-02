package com.bitcoin.autotrading.candle.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "rsi")
public class Rsi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String time_cd;

    private double rsi;
    @Builder
    public Rsi(long id, String time_cd, double rsi){
        this.id=id;
        this.time_cd=time_cd;
        this.rsi=rsi;
    }

}