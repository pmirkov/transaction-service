package com.company.transactionservice.mapper;

import com.company.transactionservice.model.TransactionResponse;
import com.company.transactionservice.persistance.Transaction;

public class TransactionMapper {

    public static TransactionResponse map(Transaction transaction){
        return TransactionResponse.builder()
                .balance(transaction.getBalance())
                .creditorAccount(transaction.getCreditorAccount())
                .debtorAccount(transaction.getDebtorAccount())
                .isSuccessful(transaction.isSuccessful())
                .transferDate(transaction.getTransferDate())
                .build();
    }
}
