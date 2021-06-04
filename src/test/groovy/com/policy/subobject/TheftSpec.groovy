package com.policy.subobject

import com.policy.visitor.PolicyVisitor
import spock.lang.Specification
import spock.lang.Subject

class TheftSpec extends Specification {

    @Subject
    Theft theft = new Theft('TV', 1.0)

    PolicyVisitor visitor = Mock()

    def 'should accept a visitor'() {
        when:
        theft.accept(visitor)

        then:
        1 * visitor.visit(theft)
    }

}
