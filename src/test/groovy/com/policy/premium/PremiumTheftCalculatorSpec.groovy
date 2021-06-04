package com.policy.premium

import com.policy.premium.PremiumTheftCalculator
import com.policy.subobject.Theft
import spock.lang.Specification
import spock.lang.Subject

class PremiumTheftCalculatorSpec extends Specification {

    @Subject
    PremiumTheftCalculator calculator = new PremiumTheftCalculator()

    def 'should calculate a zero value premium'() {
        expect:
        calculator.calculate() == 0
    }

    def 'should calculate premium for a theft sub-objects'() {
        given:
        calculator.aggregate(new Theft('Table', 14.9))

        expect:
        calculator.calculate() == 14.9 * 0.11
    }

    def 'should calculate premium for multiple theft sub-objects'() {
        given:
        calculator.aggregate(new Theft('Table', 10.3))
        calculator.aggregate(new Theft('Couch', 4.6))

        expect:
        calculator.calculate() == (10.3+4.6) * 0.11
    }

    def 'should calculate premium for insured sum amount of 15'() {
        given:
        calculator.aggregate(new Theft('Table', 10.3))
        calculator.aggregate(new Theft('Couch', 4.7))

        expect:
        calculator.calculate() == 15 * 0.05
    }

    def 'should calculate premium for insured sum amount above 15'() {
        given:
        calculator.aggregate(new Theft('Table', 10.3))
        calculator.aggregate(new Theft('Couch', 4.8))

        expect:
        calculator.calculate() == 15.1 * 0.05
    }

}
