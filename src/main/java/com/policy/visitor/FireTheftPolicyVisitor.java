package com.policy.visitor;

import com.policy.premium.Premium;
import com.policy.premium.PremiumFireCalculator;
import com.policy.premium.PremiumTheftCalculator;
import com.policy.subobject.Fire;
import com.policy.subobject.Theft;

import java.math.BigDecimal;

public class FireTheftPolicyVisitor implements PolicyVisitor {

    private PremiumFireCalculator premiumFireCalculator;
    private PremiumTheftCalculator premiumTheftCalculator;

    public FireTheftPolicyVisitor(PremiumFireCalculator premiumFireCalculator, PremiumTheftCalculator premiumTheftCalculator) {
        this.premiumFireCalculator = premiumFireCalculator;
        this.premiumTheftCalculator = premiumTheftCalculator;
    }

    public Premium getPremium() {
        BigDecimal value = premiumFireCalculator.calculate().add(premiumTheftCalculator.calculate());
        return new Premium(value);
    }

    public void visit(Theft theft) {
        premiumTheftCalculator.aggregate(theft);
    }

    public void visit(Fire fire) {
        premiumFireCalculator.aggregate(fire);
    }
}
