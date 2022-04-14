package com.company.transactionservice.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    Account findAccountById(Long id);
}
