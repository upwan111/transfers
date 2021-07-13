package com.java.example.transfers.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class Amount {

    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public Amount(BigDecimal amount) {
        this.value = amount;
    }
}
