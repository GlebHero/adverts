package com.gleb.zemskoi.testing.adverts.unit.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.PageRequest;
import com.gleb.zemskoi.adverts.entity.common.Pagination;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import com.gleb.zemskoi.adverts.service.AdvertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertServiceTest {

    @Mock
    private AdvertRepository advertRepository;

    @Spy
    private AdvertConverter advertConverter;

    @InjectMocks
    private AdvertService advertService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
