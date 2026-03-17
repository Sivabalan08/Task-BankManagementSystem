package com.siva.bankingapi.model;

import java.util.*;

public class Customer {

    private Long id;
    private String name;
    private String address;
    private List<Account> accounts = new ArrayList<>();

    public Customer() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public List<Account> getAccounts() { return accounts; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
}