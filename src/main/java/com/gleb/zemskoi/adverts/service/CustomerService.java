package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerDto findCustomerByUuid(UUID uuid) {
        Customer customer = Optional.ofNullable(customerRepository.findCustomerByUuid(uuid)).orElseThrow(() -> new NotFoundException("customer", uuid.toString()));
        return customerConverter.toCustomerDto(customer);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerConverter.toCustomer(customerDto);
        customer.setCreateDate(LocalDateTime.now());
        customer.setUuid(UUID.randomUUID());
        return customerConverter.toCustomerDto(customerRepository.save(customer));
    }
}
