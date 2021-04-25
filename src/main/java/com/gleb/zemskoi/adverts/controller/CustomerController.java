package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Customer;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
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
    public RestResponseEntity<Customer> findCustomerById(@RequestParam Long id) {
        return new RestResponseEntity<>(customerService.findCustomerById(id));
    }

    @GetMapping("name")
    public RestResponseEntity<List<Customer>> findCustomerByName(@RequestParam String name) {
        return new RestResponseEntity<>(customerService.findCustomerByName(name));
    }

    @PostMapping("save")
    public RestResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        return new RestResponseEntity<>(customerService.saveCustomer(customer));
    }
}
