package com.company.transactionservice.usecase;

import com.company.transactionservice.exception.IllegalOperationException;
import com.company.transactionservice.mapper.TransactionMapper;
import com.company.transactionservice.model.TransactionResponse;
import com.company.transactionservice.model.TransferRequest;
import com.company.transactionservice.persistance.Transaction;
import com.company.transactionservice.persistance.TransactionsRepository;
import com.company.transactionservice.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final AccountsService accountsService;

    public List<TransactionResponse> getAllAccountTransaction(Long accountId) {
        List<TransactionResponse> allTransactions = new LinkedList<>();
        allTransactions.addAll(transactionsRepository.findAllByCreditorAccount(accountId).stream()
                .map(TransactionMapper::map)
                .collect(Collectors.toList()));
        allTransactions.addAll(transactionsRepository.findAllByDebtorAccount(accountId).stream()
                .map(TransactionMapper::map)
                .collect(Collectors.toList()));

        return allTransactions;
    }

    public void processTransfer(TransferRequest request) {
        log.trace("Processing transfer request ...");
        boolean isSuccess;

        if (ValidationUtil.isTransferValid(request.getTransfer()) && !Objects.equals(request.getCreditorId(), request.getDebtorId())) {
            isSuccess = accountsService.updateAccountsBalance(request.getDebtorId(), request.getCreditorId(), request.getTransfer());
        } else {
            isSuccess = false;
        }

        transactionsRepository.save(Transaction.builder()
                .balance(request.getTransfer())
                .creditorAccount(request.getCreditorId())
                .debtorAccount(request.getDebtorId())
                .transferDate(LocalDateTime.now())
                .isSuccessful(isSuccess)
                .build());


        if(!isSuccess){
            throw new IllegalOperationException("Transfer failed");
        }
    }
}
