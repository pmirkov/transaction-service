package com.company.transactionservice.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByCreditorAccount(Long creditorAccount);

    List<Transaction> findAllByDebtorAccount(Long debtorAccount);
}
