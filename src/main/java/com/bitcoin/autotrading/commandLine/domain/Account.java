package com.bitcoin.autotrading.commandLine.domain;

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

    private	String	currency;
    private	String	balance;
    private	String	locked;
    private	String	avg_buy_price;
    private	Boolean	avg_buy_price_modified;
    private	String	unit_currency;
}