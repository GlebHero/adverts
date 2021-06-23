package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.service.CustomerService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Find customer by customer UUID")
    @LogJournal
    @GetMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.findCustomerByUuid.call.latency")
    @Counted(value = "adverts.findCustomerByUuid.call.cnt")
    public RestResponseEntity<CustomerDto> findCustomerByUuid(@PathVariable UUID customerUuid) {
        return new RestResponseEntity<>(customerService.findCustomerByUuid(customerUuid));
    }

    @Operation(summary = "Save customer")
    @LogJournal
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.saveCustomer.call.latency")
    @Counted(value = "adverts.saveCustomer.call.cnt")
    public RestResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new RestResponseEntity<>(customerService.saveCustomer(customerDto));
    }

    @Operation(summary = "Block customer by customer UUID")
    @LogJournal
    @DeleteMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.blockCustomerByUuid.call.latency")
    @Counted(value = "adverts.blockCustomerByUuid.call.cnt")
    public void blockCustomerByUuid(@PathVariable UUID customerUuid) {
        customerService.blockCustomerByUuid(customerUuid);
    }

    @Operation(summary = "Update customer by customer UUID")
    @LogJournal
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.updateCustomerByUuid.call.latency")
    @Counted(value = "adverts.updateCustomerByUuid.call.cnt")
    public RestResponseEntity<CustomerDto> updateCustomerByUuid(@Valid @RequestBody CustomerDto customerDto) {
        return new RestResponseEntity<>(customerService.updateCustomerByUuid(customerDto));
    }

    @Operation(summary = "Find all customers")
    @LogJournal
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.findAllCustomers.call.latency")
    @Counted(value = "adverts.findAllCustomers.call.cnt")
    public RestResponseEntity<List<CustomerDto>> findAllCustomers() {
        return new RestResponseEntity<>(customerService.findAllCustomers());
    }
}
