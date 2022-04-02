package com.gleb.zemskoi.adverts.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.zemskoi.adverts.config.GlobalExceptionHandler;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistrationConfig {

    @Bean
    public FilterRegistrationBean<AttachmentFilter> attachmentFilterFilterRegistrationBean(final AdvertRepository advertRepository,
                                                                                           final CustomerInfo customerInfo, final ObjectMapper objectMapper,
                                                                                           final GlobalExceptionHandler globalExceptionHandler) {
        FilterRegistrationBean<AttachmentFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AttachmentFilter(advertRepository, customerInfo, objectMapper, globalExceptionHandler));
        registrationBean.addUrlPatterns("/attachment/save/*");
        return registrationBean;
    }
}
