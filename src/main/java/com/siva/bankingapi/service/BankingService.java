package com.siva.bankingapi.service;

import com.siva.bankingapi.model.*;
import com.siva.bankingapi.exception.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankingService {

    private Map<Long, Customer> customers = new HashMap<>();
    private Map<Long, Account> accounts = new HashMap<>();

    private Long customerId = 1L;
    private Long accountId = 1001L;

    public Customer createCustomer(Customer customer){

        customer.setId(customerId++);
        customers.put(customer.getId(), customer);

        return customer;
    }

    public Collection<Customer> getAllCustomers(){
        return customers.values();
    }

    public Customer getCustomer(Long id){

        Customer c = customers.get(id);

        if(c == null){
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }

        return c;
    }
    // GET ALL ACCOUNTS
    public Collection<Account> getAllAccounts(){
        return accounts.values();
    }

    // UPDATE CUSTOMER
    public Customer updateCustomer(Long id, Customer updated){

        Customer existing = getCustomer(id);

        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());

        return existing;
    }

    // DELETE CUSTOMER
    public void deleteCustomer(Long id){

        Customer c = customers.remove(id);

        if(c == null){
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    public Account createAccount(Long customerId, double balance){

        Customer customer = customers.get(customerId);

        if(customer == null){
            throw new CustomerNotFoundException("Customer not found");
        }

        Account acc = new Account(accountId++, balance);

        accounts.put(acc.getId(), acc);

        // link line of customer and account
        customer.getAccounts().add(acc);

        return acc;
    }

    public Account getAccount(Long id){

        Account acc = accounts.get(id);

        if(acc == null){
            throw new AccountNotFoundException("Account not found with id: " + id);
        }

        return acc;
    }

    public void deposit(Long id, double amount){

        if(amount <= 0){
            throw new RuntimeException("Amount must be greater than 0");
        }

        Account acc = getAccount(id);

        acc.setBalance(acc.getBalance() + amount);
        acc.getTransactions().add(new Transaction("DEPOSIT", amount));
    }

    public void withdraw(Long id, double amount){

        Account acc = getAccount(id);

        if(amount <= 0){
            throw new RuntimeException("Amount must be greater than 0");
        }

        if(amount > acc.getBalance()){
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        acc.getTransactions().add(new Transaction("WITHDRAW", amount));
    }

    public List<Transaction> getTransactions(Long id){

        Account acc = getAccount(id);

        return acc.getTransactions();
    }
}