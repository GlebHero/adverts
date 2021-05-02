package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.entity.enums.CustomerStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerDto findCustomerByUuid(UUID uuid) {
        Customer customer = customerRepository.findCustomerByUuid(uuid);
        return customerConverter.toCustomerDto(customer);
    }

    public void blockCustomerByUuid(UUID uuid) {
        Customer customer = customerRepository.findCustomerByUuid(uuid);
        customer.setCustomerStatusEnum(CustomerStatusEnum.BLOCKED);
        customerRepository.save(customer);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerConverter.toCustomer(customerDto);
        customer.setCreateDate(LocalDateTime.now());
        customer.setUuid(UUID.randomUUID());
        customer.setCustomerStatusEnum(CustomerStatusEnum.ACTIVE);
        return customerConverter.toCustomerDto(customerRepository.save(customer));
    }
}
