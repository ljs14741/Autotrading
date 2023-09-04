package com.bitcoin.autotrading.order.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "coinorder")
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    private	String uuid;  //주문의 고유 아이디
    private	String side;  //주문 종류
    private	String ord_type;  //주문 방식
    private	Integer price;    //주문당시 가격
    private	String state;    //주문상태
    private	String market;   //마켓 키
    private	String created_at;  // 주문생성시간
    private	String volume;      // 매도시 필요
    private String portfolio;   // 수익률 -임시
    private	String remaining_volume;
    private	String reserved_fee;
    private	String remaining_fee;
    private	String paid_fee;
    private	String locked;
    private	String executed_volume;
    private	Integer	trades_count;

    // 체결데이터 - 배열로 옴
    //private	Array[Object] trades;
    private	String trades_market;
    private	String trades_uuid;
    private	String trades_price;
    private	String trades_volume;
    private	String trades_funds;
    private	String trades_side;
    private	String trades_created_at;

    @Builder
    public Order(int uuid, String side, String ord_type, int price, String state, String market, String created_at, String volume, String portfolio){
        this.uuid = String.valueOf(uuid);
        this.side = side;
        this.price=price;
        this.state=state;
        this.market=market;
        this.ord_type=ord_type;
        this.created_at=created_at;
        this.volume=volume;
        this.portfolio=portfolio;
    }


}