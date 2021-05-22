package com.gleb.zemskoi.adverts.converter;

import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper( imports = UUID.class )
public interface CustomerConverter {
    @Mapping(target = "password", ignore = true)
    CustomerDto toCustomerDto(Customer customer);
    Customer toCustomer(CustomerDto customerDto);
    Customer toCustomerClone(CustomerDto customerDto, @MappingTarget Customer advert);
}
