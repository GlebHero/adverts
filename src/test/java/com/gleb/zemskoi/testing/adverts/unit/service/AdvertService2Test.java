package com.gleb.zemskoi.testing.adverts.unit.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverterImpl;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.PageRequest;
import com.gleb.zemskoi.adverts.entity.common.Pagination;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import com.gleb.zemskoi.adverts.service.AdvertService;
import com.gleb.zemskoi.testing.adverts.unit.service.config.BeanConfigUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {BeanConfigUnit.class, AdvertConverterImpl.class})
public class AdvertService2Test {

    @MockBean
    private AdvertRepository advertRepository;

    @SpyBean
    private AdvertService advertService;

    @BeforeEach
    public void setUp() {
        List<Advert> adverts = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            adverts.add(new Advert(Long.valueOf(i), UUID.randomUUID(), "Test title" + i, "Test description" + i,
                    BigDecimal.ONE, LocalDateTime.now(), LocalDateTime.now(), AdvertStatusEnum.OPEN, new Customer()));
        }
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
