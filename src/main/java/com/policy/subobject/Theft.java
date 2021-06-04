package com.policy.subobject;

import com.policy.visitor.PolicyVisitor;

import java.math.BigDecimal;

public class Theft extends PolicySubObject {

    public Theft(String name, BigDecimal sumInsured) {
        super(name, sumInsured);
    }

    @Override
    public void accept(PolicyVisitor visitor) {
        visitor.visit(this);
    }
}
