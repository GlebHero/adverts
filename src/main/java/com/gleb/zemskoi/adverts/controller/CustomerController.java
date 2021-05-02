package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> findCustomerByUuid(@PathVariable UUID customerUuid) {
        return new RestResponseEntity<>(customerService.findCustomerByUuid(customerUuid));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customer) {
        return new RestResponseEntity<>(customerService.saveCustomer(customer));
    }

    @DeleteMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void blockCustomerByUuid(@PathVariable UUID customerUuid) {
        customerService.blockCustomerByUuid(customerUuid);
    }
}
