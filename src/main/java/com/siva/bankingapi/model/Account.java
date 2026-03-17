package com.siva.bankingapi.model;

import java.util.*;

public class Account {

    private Long id;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {}

    public Account(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void setId(Long id) { this.id = id; }
    public void setBalance(double balance) { this.balance = balance; }
}