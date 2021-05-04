package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
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
    private final CustomerRepository customerRepository;
    private final AdvertConverter advertConverter;
    private final CustomerConverter customerConverter;

    public AdvertDto findAdvertByUuid(UUID uuid) {
        Advert advert = advertRepository.findAdvertByUuid(uuid);
        return advertConverter.toAdvertDto(advert);
    }

    public List<AdvertDto> findAdvertsByCustomerId(UUID uuid, Boolean activeOnly) {
        List<AdvertDto> advertDtos = new ArrayList<>();
        if (activeOnly) {
            advertRepository.findAdvertByCustomerUuid(uuid).stream()
                    .filter(advert -> advert.getAdvertStatusEnum().equals(AdvertStatusEnum.OPEN))
                    .forEach(advert -> advertDtos.add(advertConverter.toAdvertDto(advert)));

        } else {
            advertRepository.findAdvertByCustomerUuid(uuid)
                    .forEach(advert -> advertDtos.add(advertConverter.toAdvertDto(advert)));
        }
        return advertDtos;
    }

    public AdvertDto saveAdvert(AdvertDto advertDto) {
        Advert advert = advertConverter.toAdvert(advertDto);
        advert.setUuid(UUID.randomUUID());
        advert.setCreateDate(LocalDateTime.now());
        advert.setUpdateDate(advert.getCreateDate());
        advert.setAdvertStatusEnum(AdvertStatusEnum.REVIEW);
        Customer customer = customerRepository.findCustomerByUuid(advertDto.getCustomerUuid());
        advert.setCustomer(customer);
        advertRepository.save(advert);
        return advertConverter.toAdvertDto(advertRepository.save(advert));
    }

    public void disableAdvertByUuid(UUID uuid) {
        Advert advertByUuid = advertRepository.findAdvertByUuid(uuid);
        advertByUuid.setAdvertStatusEnum(AdvertStatusEnum.CLOSED);
        advertByUuid.setUpdateDate(LocalDateTime.now());
        advertRepository.save(advertByUuid);
    }

    public RestResponseEntity<AdvertDto> updateAdvertByUuid(AdvertDto advertDto) {
        //todo check jwt. Создатель ли объявы пытается ее апдейтить.
        Advert advertByUuid = advertRepository.findAdvertByUuid(advertDto.getUuid());
        advertByUuid.setUpdateDate(LocalDateTime.now());
        advertByUuid = advertConverter.toAdvertClone(advertDto, advertByUuid);
        advertRepository.save(advertByUuid);
        return new RestResponseEntity<>(advertConverter.toAdvertDto(advertByUuid));
    }
}
