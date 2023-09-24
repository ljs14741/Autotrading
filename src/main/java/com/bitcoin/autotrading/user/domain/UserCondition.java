package com.bitcoin.autotrading.user.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_condition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long   id;
    private Long   deposit; //예수금
    private String market; //마켓(BTC)
    private Long   buyCnt; //분할 매수 횟수
    private Double sellCondition; //매도조건
    private Double buyCondition; //매수조건
    private Double takeProfitRate; //익절률
    private Double stopLossRate;   //손절률


}