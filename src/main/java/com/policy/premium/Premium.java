package com.policy.premium;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Premium {

    private BigDecimal value;

    public Premium(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.CEILING);
    }


}
