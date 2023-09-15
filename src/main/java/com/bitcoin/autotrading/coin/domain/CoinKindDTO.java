package com.bitcoin.autotrading.coin.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter //클래스의 포함된 멤버 변수의 모든 getter 매서드를 생성
@Setter
@Builder // sql에 값 넣는것
@ToString // 객체의 값 확인
//@AllArgsConstructor //생성자 자동 완성  // 이걸쓰면 밑에 CoinKindEntity Builder로 생성자 생성을 안해도되는듯
@NoArgsConstructor //생성자 자동 완성
@Entity (name="test_table")// class에 지정할 테이블명
public class CoinKindDTO {
    @Id // 이거는 무조건 들어가야하는듯 안넣으면 에러남 // pk 해당하는 컬럼
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK를 생성 전략 설정 GenerationType.SEQUENCE
    private String market;

    private String korean_name;
    private String english_name;
    private String market_warning;

    @Builder
    public CoinKindDTO(String market, String korean_name, String english_name, String market_warning) {
        this.market = market;
        this.korean_name = korean_name;
        this.english_name = english_name;
        this.market_warning = market_warning;
    }
}
