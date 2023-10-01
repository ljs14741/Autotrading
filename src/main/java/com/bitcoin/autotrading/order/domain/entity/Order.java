package com.bitcoin.autotrading.order.domain.entity;

import com.bitcoin.autotrading.order.domain.dto.OrderDTO;
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
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("side")
    private String side;

    @JsonProperty("ord_type")
    private String ordType;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("state")
    private String state;

    @JsonProperty("market")
    private String market;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("portfolio")
    private Double portfolio;

    @JsonProperty("remaining_volume")
    private Double remainingVolume;

    @JsonProperty("reserved_fee")
    private String reservedFee;

    @JsonProperty("remaining_fee")
    private String remainingFee;

    @JsonProperty("paid_fee")
    private String paidFee;

    @JsonProperty("locked")
    private String locked;

    @JsonProperty("executed_volume")
    private String executedVolume;

    @JsonProperty("trades_count")
    private Integer tradesCount;

    @JsonProperty("trades_market")
    private String tradesMarket;

    @JsonProperty("trades_uuid")
    private String tradesUuid;

    @JsonProperty("trades_price")
    private String tradesPrice;

    @JsonProperty("trades_volume")
    private String tradesVolume;

    @JsonProperty("trades_funds")
    private String tradesFunds;

    @JsonProperty("trades_side")
    private String tradesSide;

    @JsonProperty("trades_created_at")
    private String tradesCreated_at;

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