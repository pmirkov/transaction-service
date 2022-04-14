package com.company.transactionservice.api;

import com.company.transactionservice.model.TransferRequest;
import com.company.transactionservice.usecase.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody @NotNull TransferRequest request) {
        try {
            transactionService.processTransfer(request);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Transaction failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<Object> getTransactionsByAccount(@PathVariable long accountId) {
        try {
            return new ResponseEntity<>(transactionService.getAllAccountTransaction(accountId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Transaction failed", HttpStatus.BAD_REQUEST);
        }
    }
}
