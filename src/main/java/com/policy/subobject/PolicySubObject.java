package com.policy.subobject;

import com.policy.visitor.PolicyVisitor;

import java.math.BigDecimal;

public abstract class PolicySubObject  {

    private String name;
    private BigDecimal sumInsured;

    public PolicySubObject(String name) {
        this.name = name;
    }

    public PolicySubObject(String name, BigDecimal sumInsured) {
        this(name);
        this.sumInsured = sumInsured;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public abstract void accept(PolicyVisitor visitor);
}
