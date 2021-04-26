package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.entity.Advert;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;

    public Advert findAdvertById(Long id) {
        return advertRepository.findAdvertById(id);
    }

    public List<Advert> findAdvertByCustomerId(Long id) {
        return advertRepository.findAdvertByCustomerId(id);
    }

    public Advert saveAdvert(Advert advert) {
        advert.setCreateDate(LocalDateTime.now());
        return advertRepository.save(advert);
    }
}
