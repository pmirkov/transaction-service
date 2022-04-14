package com.company.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TransactionResponse {

    private long creditorAccount;

    private long debtorAccount;

    private BigDecimal balance;

    private LocalDateTime transferDate;

    private boolean isSuccessful;
}
