package com.bitcoin.autotrading.coin.domain.entity;


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
@Entity(name="kind")// class에 지정할 테이블명
@Slf4j
public class CoinKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 생성 전략 설정 GenerationType.SEQUENCE
    @Column(name = "price_id")
    private Long id;
    private String market;
    private String korean_name;
    private String english_name;
    private String market_warning;
}
