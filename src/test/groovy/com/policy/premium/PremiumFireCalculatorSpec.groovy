package com.policy.premium

import com.policy.premium.PremiumFireCalculator
import com.policy.subobject.Fire
import spock.lang.Specification
import spock.lang.Subject

class PremiumFireCalculatorSpec extends Specification {

    @Subject
    PremiumFireCalculator calculator = new PremiumFireCalculator()

    def 'should calculate a zero value premium'() {
        expect:
        calculator.calculate() == 0
    }

    def 'should calculate premium for a fire sub-objects'() {
        given:
        calculator.aggregate(new Fire('Table', 100.0))

        expect:
        calculator.calculate() == 100 * 0.014
    }

    def 'should calculate premium for multiple fire sub-objects'() {
        given:
        calculator.aggregate(new Fire('Table', 10.3))
        calculator.aggregate(new Fire('Couch', 4.7))

        expect:
        calculator.calculate() == (10.3+4.7) * 0.014
    }

    def 'should calculate premium for insured sum amount above 100'() {
        given:
        calculator.aggregate(new Fire('Table', 95.3))
        calculator.aggregate(new Fire('Couch', 4.8))

        expect:
        calculator.calculate() == 100.1 * 0.024
    }

}
