package com.bitcoin.autotrading.trading.account.domain.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    public String currency;

    @Column
    public double balance;

    @Column
    public double locked;

    @Column
    public double avg_buy_price;

    @Column
    public boolean avg_buy_price_modified;

    @Column
    public String unit_currency;

}
