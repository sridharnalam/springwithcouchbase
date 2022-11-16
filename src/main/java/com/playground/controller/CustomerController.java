package com.playground.controller;

import com.playground.model.Customer;
import com.playground.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveCustomer(@RequestBody Customer customer){
        customerRepo.save(customer);
        return "Customer saved successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers(){
        return customerRepo.findAll();
    }
}
