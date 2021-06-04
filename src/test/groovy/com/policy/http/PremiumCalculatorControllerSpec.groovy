package com.policy.http

import com.policy.Policy
import com.policy.PolicyBuilder
import com.policy.premium.PremiumCalculator
import spock.lang.Specification
import spock.lang.Subject

class PremiumCalculatorControllerSpec extends Specification {

    PremiumCalculator premiumCalculator = Mock()
    PolicyBuilder builder = Mock()

    @Subject
    PremiumCalculatorController controller = new PremiumCalculatorController(premiumCalculator, builder)

    def 'should calculate premium of an empty policy'() {
        given:
        Policy policy = new Policy('123', 'a-status')
        def policyDto = new PolicyDto()
        policyDto.number = '123'
        policyDto.status = 'a-status'

        when:
        controller.calculatePremium(policyDto)

        then:
        1 * premiumCalculator.calculate(policy)
        1 * builder.get() >> policy
        1 * builder.makePolicy(policyDto.number, policyDto.status)
        0 * _
    }

    def 'should calculate premium'() {
        given:
        Policy policy = new Policy('123', 'a-status')
        def policyDto = new PolicyDto()
        policyDto.number = '123'
        policyDto.status = 'a-status'

        def objectDto = new PolicyObjectDto()
        objectDto.name = 'obj'

        def subObjectDto = new PolicySubObjectDto()
        subObjectDto.name = 'sub'
        subObjectDto.sumInsured = 5
        subObjectDto.type = 'theft'


        objectDto.subObjects = [subObjectDto]
        policyDto.objects = [objectDto]

        when:
        controller.calculatePremium(policyDto)

        then:
        1 * premiumCalculator.calculate(policy)
        1 * builder.get() >> policy
        1 * builder.makePolicy(policyDto.number, policyDto.status)
        1 * builder.makeSubObject(subObjectDto.name, subObjectDto.sumInsured, subObjectDto.type)
        1 * builder.addObject(objectDto.name, _)
        0 * _
    }

}
