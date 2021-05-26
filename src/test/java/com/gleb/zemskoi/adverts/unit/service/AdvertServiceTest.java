package com.gleb.zemskoi.adverts.unit.service;

import com.gleb.zemskoi.adverts.AdvertsApplication;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.PageRequest;
import com.gleb.zemskoi.adverts.entity.common.Pagination;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import com.gleb.zemskoi.adverts.config.ContainersEnvironment;
import com.gleb.zemskoi.adverts.service.AdvertService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdvertsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdvertServiceTest extends ContainersEnvironment {

    @MockBean
    private AdvertRepository advertRepository;

    @Autowired
    private AdvertService advertService;

    @Before
    public void setUp() {
        List<Advert> adverts = Arrays.asList(new Advert(1L, UUID.randomUUID(), "Test title", "Test description",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(2L, UUID.randomUUID(), "Test title2", "Test description2",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(3L, UUID.randomUUID(), "Test title3", "Test description3",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(4L, UUID.randomUUID(), "Test title4", "Test description4",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(5L, UUID.randomUUID(), "Test title5", "Test description5",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(6L, UUID.randomUUID(), "Test title6", "Test description6",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(7L, UUID.randomUUID(), "Test title7", "Test description7",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(8L, UUID.randomUUID(), "Test title8", "Test description8",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(9L, UUID.randomUUID(), "Test title9", "Test description9",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()),
                new Advert(10L, UUID.randomUUID(), "Test title10", "Test description10",
                        BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()));
        Mockito.when(advertRepository.findAll())
                .thenReturn(adverts);
    }

    @Test
    public void paginationTest() {
        Data<List<AdvertDto>> allAdverts = advertService.findAllAdverts(new PageRequest(1L, 3L));
        assertEquals(3, allAdverts.getResult().size());
        assertEquals(new Pagination(1L, 3L, 4L, 10L), allAdverts.getPagination());
    }

}
