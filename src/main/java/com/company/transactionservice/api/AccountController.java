package com.company.transactionservice.api;

import com.company.transactionservice.usecase.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountsService accountsService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllAccounts() {
        try {
            return new ResponseEntity<>(accountsService.getAllAccounts(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Transaction failed", HttpStatus.BAD_REQUEST);
        }
    }
}
