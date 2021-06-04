package com.policy.subobject

import com.policy.visitor.PolicyVisitor
import spock.lang.Specification
import spock.lang.Subject

class FireSpec extends Specification {

    @Subject
    Fire fire = new Fire('TV', 1.0)

    PolicyVisitor visitor = Mock()

    def 'should accept a visitor'() {
        when:
        fire.accept(visitor)

        then:
        1 * visitor.visit(fire)
    }

}
