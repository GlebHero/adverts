package com.gleb.zemskoi.adverts.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.zemskoi.adverts.AdvertsApplication;
import com.gleb.zemskoi.adverts.config.ContainersEnvironment;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.CustomerDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdvertsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest extends ContainersEnvironment {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findCustomerByUuid() {
        CustomerDto createdCustomerDto = createCustomer("customer1.json");
        CustomerDto expectedCustomerDto = new CustomerDto();
        expectedCustomerDto.setUuid(createdCustomerDto.getUuid());
        expectedCustomerDto.setName("Ivan");
        expectedCustomerDto.setEmail("ivpet@mail.ru");
        expectedCustomerDto.setLastName("Petrov");
        expectedCustomerDto.setBirthDate(LocalDate.parse("2001-01-01"));
        ResponseEntity<RestResponseEntity<CustomerDto>> exchange = restTemplate.exchange("/customer/customerUuid/"+createdCustomerDto.getUuid().toString(), HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<>() {});
        assertEquals(expectedCustomerDto, Objects.requireNonNull(exchange.getBody()).getData().getResult());
    }

    @SneakyThrows
    private CustomerDto createCustomer(String fileName) {
        //todo configure testresttemplate for jackson
//        Customer customer = getClassPathResourceAsObject("/dto/customer/" + fileName, new TypeReference<>() {});
        String customer = Files.readString(Path.of("src/test/resources/dto/customer/" + fileName));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<String> result = restTemplate.exchange(
                "/customer/save",
                HttpMethod.POST,
                new HttpEntity<>(customer, HttpHeaders.writableHttpHeaders(headers)),
                new ParameterizedTypeReference<>() {
                }
        );
        RestResponseEntity<CustomerDto> restResponseEntity = objectMapper.readValue(result.getBody(), new TypeReference<>() {});
        return restResponseEntity.getData().getResult();
    }
}