package com.bitcoin.autotrading.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private	String uuid;
    private	String side;
    private	String ord_type;
    private	String price;
    private	String state;
    private	String market;
    private	String created_at;
    private	String volume;
    private	String remaining_volume;
    private	String reserved_fee;
    private	String remaining_fee;
    private	String paid_fee;
    private	String locked;
    private	String executed_volume;
    private	Integer	trades_count;
    //private	Array[Object] trades;
    private	String trades_market;
    private	String trades_uuid;
    private	String trades_price;
    private	String trades_volume;
    private	String trades_funds;
    private	String trades_side;
    private	String trades_created_at;


}