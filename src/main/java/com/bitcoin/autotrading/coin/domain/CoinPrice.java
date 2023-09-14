package com.bitcoin.autotrading.coin.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter //클래스의 포함된 멤버 변수의 모든 getter 매서드를 생성
@Setter
@Builder // sql에 값 넣는것
@ToString // 객체의 값 확인
@AllArgsConstructor //생성자 자동 완성
@NoArgsConstructor //생성자 자동 완성
//@RequiredArgsConstructor
@Entity(name="price")// class에 지정할 테이블명
@Slf4j
public class CoinPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 생성 전략 설정 GenerationType.SEQUENCE
    @Column(name = "price_id")
    private Long id;
    private String market;
    private String trade_date;
    private String trade_time;
    private String trade_date_kst;
    private String trade_time_kst;
    private Long trade_timestamp;
    private Double opening_price;
    private Double high_price;
    private Double low_price;
    private Double trade_price;
    private Double prev_closing_price;
    private String change;
    private Double change_price;
    private Double change_rate;
    private Double signed_change_price;
    private Double signed_change_rate;
    private Double trade_volume;
    private Double acc_trade_price;
    private Double acc_trade_price_24h;
    private Double acc_trade_volume;
    private Double acc_trade_volume_24h;
    private Double highest_52_week_price;
    private String highest_52_week_date;
    private Double lowest_52_week_price;
    private String lowest_52_week_date;
    private Long timestamp;

    @Builder
    public CoinPrice(String market, Double opening_price, Double high_price, Double low_price, Double trade_price, String change) {
        this.market = market;
        this.opening_price = opening_price;
        this.high_price = high_price;
        this.low_price = low_price;
        this.trade_price = trade_price;
        this.change = change;
    }


}
