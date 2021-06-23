package com.gleb.zemskoi.adverts.service.security;

import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final CustomerInfo customerInfo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUsername(username);
        return new User(customer.getUsername(), customer.getPassword(),
                new ArrayList<>());
    }

    public UUID getCustomerUUID(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        return customer.getUuid();
    }

    public void checkAvailabilityOperation(UUID uuid) {
        if (!uuid.equals(customerInfo.getCustomerUuid())) {
            throw new ForbiddenException();
        }
    }
}