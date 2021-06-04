package com.policy.premium

import com.policy.Policy
import com.policy.visitor.PolicyVisitor
import com.policy.visitor.PolicyVisitorFactory
import spock.lang.Specification
import spock.lang.Subject

class PremiumCalculatorSpec extends Specification {

    PolicyVisitorFactory factory = Mock()

    @Subject
    PremiumCalculator calculator = new PremiumCalculator(factory)

    def 'should evaluate a policy premium'() {
        given:
        PolicyVisitor visitor = Mock()
        Policy policy = Mock()

        when:
        calculator.calculate(policy)

        then:
        1 * factory.makePolicyVisitor() >> visitor
        1 * visitor.getPremium()
        1 * policy.accept(visitor)
    }
}
