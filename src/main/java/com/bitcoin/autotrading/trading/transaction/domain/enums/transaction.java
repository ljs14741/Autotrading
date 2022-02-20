package com.bitcoin.autotrading.trading.transaction.domain.enums;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum transaction {
    BUY,
    SELL
    ;

    public static transaction of(String name){
        return Arrays.stream(transaction.values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
