package com.siva.bankingapi.service;

import com.siva.bankingapi.model.*;
import com.siva.bankingapi.repository.*;
import com.siva.bankingapi.exception.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankingService {

    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;

    public BankingService(CustomerRepository customerRepo,
                          AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
    }

    public Customer createCustomer(Customer c){
        return customerRepo.save(c);
    }

    public Customer getCustomer(Long id){
        return customerRepo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Account createAccount(Long customerId, double balance){

        if(balance < 0){
            throw new RuntimeException("Initial balance cannot be negative");
        }

        Customer customer = getCustomer(customerId);

        Account acc = new Account();
        acc.setBalance(balance);
        acc.setCustomer(customer);

        customer.getAccounts().add(acc);

        return accountRepo.save(acc);
    }

    public Account getAccount(Long id){
        return accountRepo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public void deposit(Long id, double amount){

        if(amount <= 0){
            throw new RuntimeException("Invalid amount");
        }

        Account acc = getAccount(id);

        acc.setBalance(acc.getBalance() + amount);
        acc.getTransactions().add(new Transaction("DEPOSIT", amount));

        accountRepo.save(acc);
    }

    public void withdraw(Long id, double amount){

        Account acc = getAccount(id);

        if(amount > acc.getBalance()){
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        acc.getTransactions().add(new Transaction("WITHDRAW", amount));

        accountRepo.save(acc);
    }

    public List<Transaction> getTransactions(Long id){
        return getAccount(id).getTransactions();
    }

    public Customer updateCustomer(Long id, Customer updatedData) {
        Customer existing = getCustomer(id);
        existing.setName(updatedData.getName());
        existing.setAddress(updatedData.getAddress());
        return customerRepo.save(existing);
    }

    public void deleteCustomer(Long id) {
        Customer existing = getCustomer(id);
        customerRepo.delete(existing);
    }

    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}