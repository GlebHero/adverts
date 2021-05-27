package com.gleb.zemskoi.testing.adverts.unit.service.config;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.mq.Producer;
import com.gleb.zemskoi.adverts.service.AdvertStopWordService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootConfiguration
@MockBean({AdvertRepository.class, CustomerRepository.class, AdvertConverter.class, Producer.class, AdvertStopWordService.class})
public class BeanConfigUnit {

}
