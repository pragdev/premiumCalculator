package com.policy.premium;

import com.policy.subobject.Fire;

import java.math.BigDecimal;

public class PremiumFireCalculator {

    public static final BigDecimal FIRE_COEFFICIENT_MINIMUM = new BigDecimal("0.014");
    public static final BigDecimal FIRE_COEFFICIENT_MAXIMUM = new BigDecimal("0.024");

    public static final BigDecimal THRESHOLD = new BigDecimal("100");

    private BigDecimal sum = BigDecimal.ZERO;

    public void aggregate(Fire fire) {
        sum = sum.add(fire.getSumInsured());
    }

    public BigDecimal calculate() {

        if(sum.compareTo(THRESHOLD) > 0) {
            return sum.multiply(FIRE_COEFFICIENT_MAXIMUM);
        } else {
            return sum.multiply(FIRE_COEFFICIENT_MINIMUM);
        }

    }
}
