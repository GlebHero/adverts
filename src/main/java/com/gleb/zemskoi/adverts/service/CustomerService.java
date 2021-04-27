package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public List<CustomerDto> findCustomerByName(String name) {
        List<Customer> customerByName = customerRepository.findCustomerByName(name);
        checkIsEmptyList(customerByName, name);
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerByName.forEach(customer -> customerDtos.add(customerConverter.toCustomerDto(customer)));
        return customerDtos;
    }

    private void checkIsEmptyList(List<Customer> customerByName, String fieldValue) {
        if(customerByName == null || customerByName.isEmpty()) throw new NotFoundException("name", fieldValue);
    }

    public CustomerDto findCustomerByUuid(UUID uuid) {
        Customer customerById = customerRepository.findCustomerByUuid(uuid);
        checkIsCustomerExist(customerById, uuid);
        return customerConverter.toCustomerDto(customerById);
    }

    private void checkIsCustomerExist(Customer customerById, UUID uuid) {
        if(customerById == null) throw new NotFoundException("id", uuid.toString());
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerConverter.toCustomer(customerDto);
        customer.setCreateDate(LocalDateTime.now());
        customer.setUuid(UUID.randomUUID());
        return customerConverter.toCustomerDto(customerRepository.save(customer));
    }
}
