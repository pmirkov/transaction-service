package com.company.transactionservice.validation

import spock.lang.Specification

class ValidationUtilTest extends Specification {

    def "when account balance is less than 0 validator should return false"() {
        given: "incorrect balance"
        def balance = BigDecimal.ZERO

        when:
        def result = ValidationUtil.isTransferValid(balance)

        then:
        !result
    }
}
