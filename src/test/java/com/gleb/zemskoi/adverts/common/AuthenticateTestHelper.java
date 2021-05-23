package com.gleb.zemskoi.adverts.common;

import com.gleb.zemskoi.adverts.entity.common.JwtRequest;
import com.gleb.zemskoi.adverts.entity.common.JwtResponse;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AuthenticateTestHelper {

    @Autowired
    private TestRestTemplate restTemplate;

    public HttpHeaders authenticate(JwtRequest jwtRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<RestResponseEntity<JwtResponse>> result = restTemplate.exchange(
                "/authenticate",
                HttpMethod.POST,
                new HttpEntity<>(jwtRequest, HttpHeaders.writableHttpHeaders(headers)),
                new ParameterizedTypeReference<>() {
                }
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", List.of("Bearer " + result.getBody().getData().getResult().getJwtToken()));
        return httpHeaders;
    }
}
