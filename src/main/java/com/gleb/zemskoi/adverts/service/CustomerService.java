package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.entity.enums.CustomerStatusEnum;
import com.gleb.zemskoi.adverts.service.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final PasswordEncoder bcryptEncoder;
    private final JwtUserDetailsService jwtUserDetailsService;

    public CustomerDto findCustomerByUuid(UUID uuid) {
        Customer customer = customerRepository.findCustomerByUuid(uuid);
        return customerConverter.toCustomerDto(customer);
    }

    public void blockCustomerByUuid(UUID uuid) {
        Customer customer = customerRepository.findCustomerByUuid(uuid);
        customer.setCustomerStatusEnum(CustomerStatusEnum.BLOCKED);
        customer.setUpdateDate(LocalDateTime.now());
        customerRepository.save(customer);
    }

    public List<CustomerDto> findAllCustomers() {
        return customerConverter.toCustomerDtoList(customerRepository.findAll());
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerConverter.toCustomer(customerDto);
        customer.setUsername(customer.getUsername());
        customer.setPassword(bcryptEncoder.encode(customer.getPassword()));
        customer.setCreateDate(LocalDateTime.now());
        customer.setUpdateDate(customer.getCreateDate());
        customer.setUuid(UUID.randomUUID());
        customer.setCustomerStatusEnum(CustomerStatusEnum.ACTIVE);
        return customerConverter.toCustomerDto(customerRepository.save(customer));
    }

    public CustomerDto updateCustomerByUuid(CustomerDto customerDto) {
        jwtUserDetailsService.checkAvailabilityOperation(customerDto.getUuid()); //todo make this check in some filter
        Customer customerByUuid = customerRepository.findCustomerByUuid(customerDto.getUuid());
        customerByUuid.setUpdateDate(LocalDateTime.now());
        Customer customer = customerConverter.toCustomerClone(customerDto, customerByUuid);
        customerRepository.save(customer);
        return customerConverter.toCustomerDto(customer);
    }
}
