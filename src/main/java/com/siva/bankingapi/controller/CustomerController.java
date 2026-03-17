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

    // CREATE
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return service.createCustomer(customer);
    }

    // GET ALL
    @GetMapping
    public Collection<Customer> getCustomers(){
        return service.getAllCustomers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return service.getCustomer(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @RequestBody Customer updated){

        return service.updateCustomer(id, updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id){

        service.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}