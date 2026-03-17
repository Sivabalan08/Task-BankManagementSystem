package com.siva.bankingapi.controller;

import com.siva.bankingapi.model.Customer;
import com.siva.bankingapi.service.BankingService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final BankingService service;

    public CustomerController(BankingService service) {
        this.service = service;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return service.createCustomer(customer);
    }

    @GetMapping
    public Collection<Customer> getCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return service.getCustomer(id);
    }
}