package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Customer;
import com.gleb.zemskoi.adverts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("id")
    public Customer findCustomerById(@RequestParam Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("name")
    public List<Customer> findCustomerByName(@RequestParam String name) {
        return customerService.findCustomerByName(name);
    }

    @PostMapping("save")
    public Customer saveCustomer(@Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
