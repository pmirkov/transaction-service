package com.company.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferRequest {
    @NotNull
    private Long debtorId;
    @NotNull
    private Long creditorId;
    @NotNull
    private BigDecimal transfer;
}
