package com.policy.premium;

import com.policy.Policy;
import com.policy.visitor.PolicyVisitor;
import com.policy.visitor.PolicyVisitorFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PremiumCalculator {

    private PolicyVisitorFactory visitorFactory;

    @Inject
    public PremiumCalculator(PolicyVisitorFactory policyVisitor) {
        this.visitorFactory = policyVisitor;
    }

    public Premium calculate(Policy policy) {

        PolicyVisitor visitor = visitorFactory.makePolicyVisitor();
        policy.accept(visitor);
        return visitor.getPremium();
    }

}
