package com.gleb.zemskoi.adverts.dao;

import com.gleb.zemskoi.adverts.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findCustomerByName(String name);
    Customer findCustomerById(Long id);

}
