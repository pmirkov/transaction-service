package com.company.transactionservice.mapper;

import com.company.transactionservice.model.AccountsResponse;
import com.company.transactionservice.persistance.Account;

public class AccountsMapper {

    public static AccountsResponse map(Account account){
        return AccountsResponse.builder()
                .holderName(account.getHolderName())
                .build();
    }
}
