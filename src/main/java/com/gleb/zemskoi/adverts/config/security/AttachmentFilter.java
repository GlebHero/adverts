package com.gleb.zemskoi.adverts.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.zemskoi.adverts.config.GlobalExceptionHandler;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
public class AttachmentFilter extends OncePerRequestFilter {

    private final AdvertRepository advertRepository;
    private final CustomerInfo customerInfo;
    private final ObjectMapper objectMapper;
    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String[] parts = httpServletRequest.getRequestURI().split("/");
            int indexOfName = Arrays.asList(parts).indexOf("save");
            Advert advertByUuid = advertRepository.findAdvertByUuid(UUID.fromString(parts[indexOfName + 1]));
            if (customerInfo.getCustomerUuid().equals(advertByUuid.getCustomer().getUuid())) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                throw new ForbiddenException();
            }
        } catch (ForbiddenException e) {
            ResponseEntity<Object> objectResponseEntity = globalExceptionHandler.handleForbiddenException(e);
            httpServletResponse.setStatus(objectResponseEntity.getStatusCodeValue());
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(objectResponseEntity));//todo fix object mapper
        }
    }
}
