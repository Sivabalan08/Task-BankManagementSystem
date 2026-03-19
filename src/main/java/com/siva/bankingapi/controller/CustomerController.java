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
    public Customer create(@RequestBody Customer c){
        return service.createCustomer(c);
    }

    @GetMapping
    public List<Customer> getAll(){
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id){
        return service.getCustomer(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer c) {
        return service.updateCustomer(id, c);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}