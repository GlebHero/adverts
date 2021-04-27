package com.gleb.zemskoi.adverts.converter;

import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper( imports = UUID.class )
public interface CustomerConverter {
    CustomerDto toCustomerDto(Customer customer);
    Customer toCustomer(CustomerDto customerDto);
}
