package com.company.transactionservice.persistance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "creditor")
    private long creditorAccount;

    @Column(name = "debtor")
    private long debtorAccount;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "transfer_date")
    private LocalDateTime transferDate;

    @Column(name = "is_successful")
    private boolean isSuccessful;
}
