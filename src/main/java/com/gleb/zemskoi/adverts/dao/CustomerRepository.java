package com.gleb.zemskoi.adverts.dao;

import com.gleb.zemskoi.adverts.aop.nullchecker.NotNullResult;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @NotNullResult
    Customer findCustomerByUuid(UUID uuid);

    @NotNullResult
    Customer findCustomerByUsername(String username);

    List<Customer> findAll();

}
