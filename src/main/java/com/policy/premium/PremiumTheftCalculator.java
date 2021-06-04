package com.policy.premium;

import com.policy.subobject.Theft;

import java.math.BigDecimal;

public class PremiumTheftCalculator {

    public static final BigDecimal COEFFICIENT_MINIMUM = new BigDecimal("0.11");
    public static final BigDecimal COEFFICIENT_MAXIMUM = new BigDecimal("0.05");

    public static final BigDecimal THRESHOLD = new BigDecimal("15");

    private BigDecimal sum = BigDecimal.ZERO;

    public void aggregate(Theft theft) {
        sum = sum.add(theft.getSumInsured());
    }

    public BigDecimal calculate() {

        if(sum.compareTo(THRESHOLD) >= 0) {
            return sum.multiply(COEFFICIENT_MAXIMUM);
        } else {
            return sum.multiply(COEFFICIENT_MINIMUM);
        }

    }
}
