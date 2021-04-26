package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Customer;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<Customer> findCustomerById(@RequestParam Long id) {
        return new RestResponseEntity<>(customerService.findCustomerById(id));
    }

    @GetMapping(value = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<List<Customer>> findCustomerByName(@RequestParam String name) {
        return new RestResponseEntity<>(customerService.findCustomerByName(name));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
        return new RestResponseEntity<>(customerService.saveCustomer(customer));
    }
}
