package com.bitcoin.autotrading.account.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "account")
@ToString
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private	String	currency; /* 화폐를 의미하는 영문 대문자 코드 */
    private	String	balance; /* 주문가능 금액/수량 */
    private	Integer	locked; /* 주문 중 묶여있는 금액/수량 */
    private	Integer	avg_buy_price; /* 매수평균가 */
    private	Boolean	avg_buy_price_modified; /* 매수평균가 수정 여부 */
    private	String	unit_currency; /* 평단가 기준 화폐 */

    @Builder
    public Account(String currency, String balance, int locked, int avg_buy_price, Boolean avg_buy_price_modified, String unit_currency){
        this.currency=currency;
        this.balance=balance;
        this.locked=locked;
        this.avg_buy_price=avg_buy_price;
        this.avg_buy_price_modified=avg_buy_price_modified;
        this.unit_currency=unit_currency;
    }
}