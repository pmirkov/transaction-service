package com.company.transactionservice.validation;

import com.company.transactionservice.exception.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ValidationUtil {

    public static boolean isTransferValid(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        log.info("Balance is not correct {}", balance);
        return false;
    }
}
