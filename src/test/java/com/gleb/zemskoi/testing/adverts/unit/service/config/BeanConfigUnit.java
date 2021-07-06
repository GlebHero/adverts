package com.gleb.zemskoi.testing.adverts.unit.service.config;

import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import com.gleb.zemskoi.adverts.mq.Producer;
import com.gleb.zemskoi.adverts.service.AdvertStopWordService;
import com.gleb.zemskoi.adverts.service.security.JwtUserDetailsService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootConfiguration
@MockBean({AdvertRepository.class, CustomerRepository.class, Producer.class,
        AdvertStopWordService.class, CustomerInfo.class, JwtUserDetailsService.class})
public class BeanConfigUnit {

}
