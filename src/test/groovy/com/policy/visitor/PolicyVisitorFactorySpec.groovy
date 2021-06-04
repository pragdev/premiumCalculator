package com.policy.visitor

import spock.lang.Specification
import spock.lang.Subject

class PolicyVisitorFactorySpec extends Specification {

    @Subject
    PolicyVisitorFactory factory = new PolicyVisitorFactory()

    def 'should make a policy visitor'() {
        expect:
        factory.makePolicyVisitor() in PolicyVisitor
    }
}
