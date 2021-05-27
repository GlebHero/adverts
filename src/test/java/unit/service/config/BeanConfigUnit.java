package unit.service.config;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.mq.Producer;
import com.gleb.zemskoi.adverts.service.AdvertService;
import com.gleb.zemskoi.adverts.service.AdvertStopWordService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@MockBean({AdvertRepository.class, CustomerRepository.class, AdvertConverter.class, Producer.class, AdvertStopWordService.class})
public class BeanConfigUnit {

    @Bean
    public AdvertService advertService(AdvertRepository advertRepository, CustomerRepository customerRepository,
                                       AdvertConverter advertConverter, Producer producer, AdvertStopWordService advertStopWordService) {
        return new AdvertService(advertRepository, customerRepository, advertConverter, producer, advertStopWordService);
    }

}
