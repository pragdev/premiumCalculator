package com.policy

import com.policy.subobject.Fire
import com.policy.subobject.Theft
import spock.lang.Specification
import spock.lang.Subject

class PolicyBuilderSpec extends Specification {

    @Subject
    PolicyBuilder policyBuilder = new PolicyBuilder()

    def 'should build an empty policy'() {
        when:
        policyBuilder.makePolicy('123', 'a-status')

        then:
        def policy = policyBuilder.get()
        policy in Policy
        policy.number == '123'
        policy.status == 'a-status'
        policy.objects.empty
    }


    def 'should build a policy with an object'() {
        when:
        policyBuilder.makePolicy('123', 'a-status')
        policyBuilder.addObject('an-object', [])

        then:
        def policy = policyBuilder.get()
        def object = policy.objects.first()
        object in PolicyObject
        object.name == 'an-object'
        object.subObjects.empty
    }

    def 'should build a policy with sub-objects'() {
        when:
        policyBuilder.makePolicy('123', 'a-status')
        def fire = policyBuilder.makeSubObject('a-fire-sub-object', 10.0, 'fire')
        def theft = policyBuilder.makeSubObject('a-theft-sub-object', 20.0, 'theft')

        policyBuilder.addObject('an-object', [fire, theft])

        then:
        def policy = policyBuilder.get()
        def object = policy.objects.first()
        object.subObjects.size() == 2
        object.subObjects.any {
            it.name == 'a-fire-sub-object'
            && it in Fire
            && it.sumInsured == 10.0
        }

        object.subObjects.any {
            it.name == 'a-theft-sub-object'
                    && it in Theft
                    && it.sumInsured == 20.0
        }
    }

    def 'should only support fire and theft sub objects'() {
        when:
        policyBuilder.makePolicy('123', 'a-status')
        def wrong = policyBuilder.makeSubObject('a-wrong-sub-object', 10.0, 'wrong')
        policyBuilder.addObject('an-object', [wrong])

        then:
        thrown IllegalArgumentException
    }

}
