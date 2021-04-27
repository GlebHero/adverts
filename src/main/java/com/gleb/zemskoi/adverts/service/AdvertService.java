package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final AdvertConverter advertConverter;

    public AdvertDto findAdvertByUuid(UUID uuid) {
        return advertConverter.toAdvertDto(advertRepository.findAdvertByUuid(uuid));
    }

    public List<AdvertDto> findAdvertByCustomerId(UUID uuid) {
        List<AdvertDto> advertDtos = new ArrayList<>();
        advertRepository.findAdvertByCustomerUuid(uuid).forEach(advert -> advertDtos.add(advertConverter.toAdvertDto(advert)));
        return advertDtos;
    }

    public AdvertDto saveAdvert(AdvertDto advertDto) {
        Advert advert = advertConverter.toAdvert(advertDto);
        advert.setCreateDate(LocalDateTime.now());
        advert.setUuid(UUID.randomUUID());
        return advertConverter.toAdvertDto(advertRepository.save(advert));
    }
}
