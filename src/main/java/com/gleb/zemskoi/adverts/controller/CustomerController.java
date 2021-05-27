package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Find customer by customer UUID")
    @LogJournal
    @GetMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> findCustomerByUuid(@PathVariable UUID customerUuid) {
        return new RestResponseEntity<>(customerService.findCustomerByUuid(customerUuid));
    }

    @Operation(summary = "Save customer")
    @LogJournal
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new RestResponseEntity<>(customerService.saveCustomer(customerDto));
    }

    @Operation(summary = "Block customer by customer UUID")
    @LogJournal
    @DeleteMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void blockCustomerByUuid(@PathVariable UUID customerUuid) {
        customerService.blockCustomerByUuid(customerUuid);
    }

    @Operation(summary = "Update customer by customer UUID")
    @LogJournal
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<CustomerDto> updateCustomerByUuid(@Valid @RequestBody CustomerDto customerDto) {
        return new RestResponseEntity<>(customerService.updateCustomerByUuid(customerDto));
    }
}
