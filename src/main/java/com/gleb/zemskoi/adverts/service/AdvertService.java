package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.exception.NotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final CustomerRepository customerRepository;
    private final AdvertConverter advertConverter;
    private final CustomerConverter customerConverter;

    public AdvertDto findAdvertByUuid(UUID uuid) {
        Advert advert = Optional.ofNullable(advertRepository.findAdvertByUuid(uuid)).orElseThrow(() -> new NotFoundException("advert", uuid.toString()));
        return advertConverter.toAdvertDto(advert);
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
        Customer customer = customerRepository.findCustomerByUuid(advertDto.getCustomerUuid());
        advert.setCustomer(customer);
        advertRepository.save(advert);
        return advertConverter.toAdvertDto(advertRepository.save(advert));
    }
}
