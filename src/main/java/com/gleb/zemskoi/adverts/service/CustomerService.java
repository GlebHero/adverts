package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.Customer;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findCustomerByName(String name) {
        List<Customer> customerByName = customerRepository.findCustomerByName(name);
        if(customerByName == null || customerByName.isEmpty()) throw new NotFoundException("name", name);
        return customerByName;
    }

    public Customer findCustomerById(Long id) {
        Customer customerById = customerRepository.findCustomerById(id);
        if(customerById == null) throw new NotFoundException("id", id.toString());
        return customerById;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
