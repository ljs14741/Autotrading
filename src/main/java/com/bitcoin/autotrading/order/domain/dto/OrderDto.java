package com.bitcoin.autotrading.order.domain.dto;

import com.bitcoin.autotrading.order.domain.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * DTO for {@link com.bitcoin.autotrading.order.domain.Order}
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class OrderDto{


    private String uuid;
    private String side;
    private String ord_type;
    private Double price;
    private String state;
    private String market;
    private String created_at;
    private Double volume;
    private Double portfolio;
    private Double remaining_volume;
    private String reserved_fee;
    private String remaining_fee;
    private String paid_fee;
    private String locked;
    private String executed_volume;
    private Integer trades_count;
    private String trades_market;
    private String trades_uuid;
    private String trades_price;
    private String trades_volume;
    private String trades_funds;
    private String trades_side;
    private String trades_created_at;

    public static OrderDto toDto(Order order){
        return OrderDto.builder()
                .created_at(order.getCreated_at())
                .side(order.getSide())
                .uuid(order.getUuid())
                .ord_type(order.getOrd_type())
                .price(order.getPrice())
                .state(order.getState())
                .market(order.getMarket())
                .volume(order.getVolume())
                .portfolio(order.getPortfolio())
                .build();
    }


}