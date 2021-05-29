package com.gleb.zemskoi.adverts.config;

import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.Clock;
import java.util.Locale;

@Configuration
public class BeanConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    @RequestScope
    public CustomerInfo customerInfo() {
        return new CustomerInfo();
    }
}
