package com.policy


import com.policy.visitor.PolicyVisitor
import spock.lang.Specification

class PolicySpec extends Specification {

    def 'should accept a visitor'() {
        given:
        PolicyVisitor visitor = Mock()
        PolicyObject policyObject = Mock()

        def policy = new Policy('123', 'a-status')
        policy.addObject(policyObject)

        when:
        policy.accept(visitor)

        then:
        1 * policyObject.accept(visitor)
    }
}
