package com.siva.bankingapi.controller;

import com.siva.bankingapi.model.*;
import com.siva.bankingapi.service.BankingService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final BankingService service;

    public AccountController(BankingService service) {
        this.service = service;
    }

    // CREATE ACCOUNT (linked to customer)
    @PostMapping
    public Account createAccount(@RequestParam Long customerId,
                                 @RequestParam double balance){

        return service.createAccount(customerId, balance);
    }

    // GET ALL
    @GetMapping
    public Collection<Account> getAccounts(){
        return service.getAllAccounts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id){
        return service.getAccount(id);
    }

    // DEPOSIT
    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id,
                          @RequestParam double amount){

        service.deposit(id, amount);
        return "Deposit successful";
    }

    // WITHDRAW
    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id,
                           @RequestParam double amount){

        service.withdraw(id, amount);
        return "Withdrawal successful";
    }

    // TRANSACTIONS
    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id){

        return service.getTransactions(id);
    }
}