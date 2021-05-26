package com.gleb.zemskoi.adverts.integration.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gleb.zemskoi.adverts.AdvertsApplication;
import com.gleb.zemskoi.adverts.config.ContainersEnvironment;
import com.gleb.zemskoi.adverts.entity.common.JwtRequest;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import com.gleb.zemskoi.adverts.integration.common.AuthenticateTestHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;

import static com.gleb.zemskoi.adverts.integration.common.TestUtils.getClassPathResourceAsObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvertsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest extends ContainersEnvironment {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AuthenticateTestHelper authenticateTestHelper;

    @Test
    public void findCustomerByUuid() {
        CustomerDto createdCustomerDto = createCustomer("customer1.json");
        CustomerDto expectedCustomerDto = new CustomerDto();
        expectedCustomerDto.setUuid(createdCustomerDto.getUuid());
        expectedCustomerDto.setName("Ivan");
        expectedCustomerDto.setEmail("ivpet@gmail.com");
        expectedCustomerDto.setLastName("Petrov");
        expectedCustomerDto.setUsername("qwe123");
        expectedCustomerDto.setPhoneNumber("8189756420");
        expectedCustomerDto.setBirthDate(LocalDate.parse("2000-01-01"));
        ResponseEntity<RestResponseEntity<CustomerDto>> exchange = getExchange(createdCustomerDto);
        assertEquals(expectedCustomerDto, Objects.requireNonNull(exchange.getBody()).getData().getResult());
    }

    private ResponseEntity<RestResponseEntity<CustomerDto>> getExchange(CustomerDto createdCustomerDto) {
        HttpHeaders authenticate = authenticateTestHelper.authenticate(new JwtRequest(createdCustomerDto.getUsername(), "s123"));
        return restTemplate.exchange("/customer/customerUuid/" + createdCustomerDto.getUuid().toString(), HttpMethod.GET, new HttpEntity<>(authenticate), new ParameterizedTypeReference<>() {
        });
    }

    @SneakyThrows
    private CustomerDto createCustomer(String fileName) {
        Customer customer = getClassPathResourceAsObject("/dto/customer/" + fileName, new TypeReference<>() {});
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<RestResponseEntity<CustomerDto>> result = restTemplate.exchange(
                "/customer/save",
                HttpMethod.POST,
                new HttpEntity<>(customer, HttpHeaders.writableHttpHeaders(headers)),
                new ParameterizedTypeReference<>() {
                }
        );
        return result.getBody().getData().getResult();
    }
}