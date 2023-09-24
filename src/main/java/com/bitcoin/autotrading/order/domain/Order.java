package com.bitcoin.autotrading.order.domain;

import com.bitcoin.autotrading.order.domain.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "coinorder")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    private	String uuid;  //주문의 고유 아이디

    private	String side;  //주문 종류
    private	String ord_type;  //주문 방식
    private	Double price;    //주문당시 가격
    private	String state;    //주문상태
    private	String market;   //마켓 키
    private	String created_at;  // 주문생성시간
    private	Double volume;      // 매도시 필요
    private Double portfolio;   // 수익률 -임시
    private	Double remaining_volume;
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

    public static Order toEntity(OrderDto dto){
        return Order.builder()
                .created_at(dto.getCreated_at())
                .locked(dto.getLocked())
                .market(dto.getMarket())
                .ord_type(dto.getOrd_type())
                .executed_volume(dto.getExecuted_volume())
                .paid_fee(dto.getPaid_fee())
                .portfolio(dto.getPortfolio())
                .price(dto.getPrice())
                .volume(dto.getVolume())
                .remaining_fee(dto.getRemaining_fee())
                .remaining_volume(dto.getRemaining_volume())
                .side(dto.getSide())
                .state(dto.getState())
                .build();
    }

}