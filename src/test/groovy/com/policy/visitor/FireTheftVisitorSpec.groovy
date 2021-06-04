package com.policy.visitor


import com.policy.premium.PremiumFireCalculator
import com.policy.premium.PremiumTheftCalculator
import com.policy.subobject.Fire
import com.policy.subobject.Theft
import com.policy.visitor.FireTheftPolicyVisitor
import com.policy.visitor.PolicyVisitor
import spock.lang.Specification
import spock.lang.Subject

class FireTheftVisitorSpec extends Specification {

    // TODO assumption: no need to handle more policies

    PremiumFireCalculator fireCalculator = Mock()
    PremiumTheftCalculator theftCalculator = Mock()

    @Subject
    PolicyVisitor visitor = new FireTheftPolicyVisitor(fireCalculator, theftCalculator)

    def 'should visit a fire sub-object'() {
        given:
        def fire = new Fire('tv', 0.0)

        when:
        visitor.visit(fire)

        then:
        1 * fireCalculator.aggregate(fire)
        0 * _
    }


    def 'should visit a theft sub-object'() {
        given:
        def theft = new Theft('tv', 0.0)

        when:
        visitor.visit(theft)

        then:
        1 * theftCalculator.aggregate(theft)
        0 * _
    }

    def 'should calculate the premium as sum of fire premium and theft premium'() {
        when:
        def premium = visitor.getPremium()

        then:
        1 * fireCalculator.calculate() >> 10.0
        1 * theftCalculator.calculate() >> 15.3
        premium.value == 10.0 + 15.3
    }
}
