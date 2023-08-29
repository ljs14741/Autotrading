package com.bitcoin.autotrading.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private	String	currency; /* 화폐를 의미하는 영문 대문자 코드 */
    private	String	balance; /* 주문가능 금액/수량 */
    private	String	locked; /* 주문 중 묶여있는 금액/수량 */
    private	String	avg_buy_price; /* 매수평균가 */
    private	Boolean	avg_buy_price_modified; /* 매수평균가 수정 여부 */
    private	String	unit_currency; /* 평단가 기준 화폐 */
}