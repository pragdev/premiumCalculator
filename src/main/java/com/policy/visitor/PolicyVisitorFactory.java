package com.policy.visitor;

import com.policy.premium.PremiumFireCalculator;
import com.policy.premium.PremiumTheftCalculator;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;

@Factory
public class PolicyVisitorFactory {

    @Prototype
    public PolicyVisitor makePolicyVisitor() {
        return new FireTheftPolicyVisitor(new PremiumFireCalculator(), new PremiumTheftCalculator());
    }

}
