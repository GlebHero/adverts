package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> findCustomerById(@RequestParam UUID uuid) {
        return new RestResponseEntity<>(customerService.findCustomerByUuid(uuid));
    }

    @GetMapping(value = "name", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<List<CustomerDto>> findCustomerByName(@RequestParam String name) {
        return new RestResponseEntity<>(customerService.findCustomerByName(name));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customer) {
        return new RestResponseEntity<>(customerService.saveCustomer(customer));
    }
}
