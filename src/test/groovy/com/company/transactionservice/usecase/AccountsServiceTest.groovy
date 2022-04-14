package com.company.transactionservice.usecase

import com.company.transactionservice.persistance.Account
import com.company.transactionservice.persistance.AccountsRepository
import org.spockframework.spring.SpringBean
import spock.lang.Specification
import spock.lang.Subject

class AccountsServiceTest extends Specification {

    @Subject
    AccountsService service

    @SpringBean
    AccountsRepository repository = Mock()

    def setup() {
        service = new AccountsService(repository)
    }

    def "when account exist account service should return correct list"() {
        given:
        def holderName = "TEST_HOLDER_NAME"
        def mail = "TEST@MAIL.COM"
        def balance = BigDecimal.TEN
        def accountId = 12

        and:
        repository.findAll() >>
                List.of(new Account(accountId, holderName, mail, balance))

        when:
        def result = service.getAllAccounts()

        then:
        result != null
        result.size() > 0
        result.get(0).getHolderName() == holderName
    }

    def "when account has invalid balance transfer should fail"() {
        given:
        def debtorAccountId = 12
        def creditorAccountId = 11

        and:
        repository.findAccountById(creditorAccountId) >>
                new Account(creditorAccountId, "TEST_HOLDER", "TEST@MAIL", BigDecimal.ZERO)

        repository.findAccountById(debtorAccountId) >>
                new Account(creditorAccountId, "TEST_HOLDER", "TEST@MAIL", BigDecimal.ZERO)

        when:
        def result = service.updateAccountsBalance(debtorAccountId, creditorAccountId, BigDecimal.TEN)

        then:
        !result
    }
}
