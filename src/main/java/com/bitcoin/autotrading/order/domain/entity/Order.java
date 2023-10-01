package com.bitcoin.autotrading.order.domain.entity;

import com.bitcoin.autotrading.order.domain.dto.OrderDTO;
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
    private	String ordType;  //주문 방식
    private	Double price;    //주문당시 가격
    private	String state;    //주문상태
    private	String market;   //마켓 키
    private	String createdAt;  // 주문생성시간
    private	Double volume;      // 매도시 필요
    private Double portfolio;   // 수익률 -임시
    private	Double remainingVolume;
    private	String reservedFee;
    private	String remainingFee;
    private	String paidFee;
    private	String locked;
    private	String executedVolume;
    private	Integer	tradesCount;

    // 체결데이터 - 배열로 옴
    //private	Array[Object] trades;
    private	String tradesMarket;
    private	String tradesUuid;
    private	String tradesPrice;
    private	String tradesVolume;
    private	String tradesFunds;
    private	String tradesSide;
    private	String tradesCreatedAt;

    public static Order toEntity(OrderDTO dto){
        return Order.builder()
                .createdAt(dto.getCreatedAt())
                .locked(dto.getLocked())
                .market(dto.getMarket())
                .ordType(dto.getOrdType())
                .executedVolume(dto.getExecutedVolume())
                .paidFee(dto.getPaidFee())
                .portfolio(dto.getPortfolio())
                .price(dto.getPrice())
                .volume(dto.getVolume())
                .remainingFee(dto.getRemainingFee())
                .remainingVolume(dto.getRemainingVolume())
                .side(dto.getSide())
                .state(dto.getState())
                .build();
    }

}