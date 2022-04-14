package com.company.transactionservice.usecase;

import com.company.transactionservice.mapper.AccountsMapper;
import com.company.transactionservice.model.AccountsResponse;
import com.company.transactionservice.persistance.Account;
import com.company.transactionservice.persistance.AccountsRepository;
import com.company.transactionservice.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public List<AccountsResponse> getAllAccounts(){
       return accountsRepository.findAll().stream()
                .map(AccountsMapper::map)
                .collect(Collectors.toList());
    }

    protected boolean updateAccountsBalance(long debtorId, long creditorId, BigDecimal transfer) {
        Account creditorAccount = accountsRepository.findAccountById(creditorId);
        Account debtorAccount = accountsRepository.findAccountById(debtorId);

        BigDecimal currentDebtorBalance = debtorAccount.getBalance();
        debtorAccount.setBalance(currentDebtorBalance.add(transfer));

        BigDecimal currentCreditorBalance = creditorAccount.getBalance();
        currentCreditorBalance = currentCreditorBalance.subtract(transfer);

        if (!ValidationUtil.isTransferValid(currentCreditorBalance)) {
            log.error("Creditor does not have sufficient resources.");
            return false;
        }
        creditorAccount.setBalance(currentCreditorBalance);

        try {
            accountsRepository.save(debtorAccount);
            accountsRepository.save(creditorAccount);
        } catch (Exception e) {
            log.error("Error occurs when update debtor account {}", e.getMessage());
            return false;
        }

        log.info("Transfer successfully completed");
        return true;
    }


}
