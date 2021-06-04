package com.policy

import com.policy.subobject.Theft
import com.policy.visitor.PolicyVisitor
import spock.lang.Specification

class PolicyObjectSpec extends Specification {

    def 'should accept a visitor'() {
        given:
        PolicyVisitor visitor = Mock()
        Theft theft = Mock()
        def object = new PolicyObject('name', [theft])

        when:
        object.accept(visitor)

        then:
        1 * theft.accept(visitor)
    }
}
