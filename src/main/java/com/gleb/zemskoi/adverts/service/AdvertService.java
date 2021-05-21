package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.converter.AdvertConverter;
import com.gleb.zemskoi.adverts.converter.CustomerConverter;
import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.CustomerRepository;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.PageRequest;
import com.gleb.zemskoi.adverts.entity.common.Pagination;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Customer;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.enums.AdvertStatusEnum;
import com.gleb.zemskoi.adverts.entity.filter.AdvertFilter;
import com.gleb.zemskoi.adverts.mq.Producer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@lombok.Data
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final CustomerRepository customerRepository;
    private final AdvertConverter advertConverter;
    private final CustomerConverter customerConverter;
    private final Producer producer;
    private final AdvertStopWordService advertStopWordService;

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
        Advert advert = setValuesForNewAdvert(advertDto);
        advert = advertRepository.save(advert);
        producer.sendAdvertForReview(advert);
        return advertConverter.toAdvertDto(advert);
    }

    public void closeAdvertByUuid(UUID uuid) {
        Advert advertByUuid = advertRepository.findAdvertByUuid(uuid);
        advertByUuid.setAdvertStatusEnum(AdvertStatusEnum.CLOSED);
        advertByUuid.setUpdateDate(LocalDateTime.now());
        advertRepository.save(advertByUuid);
    }

    public AdvertDto updateAdvertByUuid(AdvertDto advertDto) {
        //todo check jwt. Check who exactly wants to update advert.
        Advert advertByUuid = advertRepository.findAdvertByUuid(advertDto.getUuid());
        advertByUuid.setUpdateDate(LocalDateTime.now());
        advertByUuid = advertConverter.toAdvertClone(advertDto, advertByUuid);
        advertRepository.save(advertByUuid);
        return advertConverter.toAdvertDto(advertByUuid);
    }


    public void changeAdvertStatus(Advert advert) {
        if (advertStopWordService.containsBadWord(advert)) {
            advert.setAdvertStatusEnum(AdvertStatusEnum.CLOSED);
        } else {
            advert.setAdvertStatusEnum(AdvertStatusEnum.OPEN);
        }
        advertRepository.save(advert);
    }

    private Advert setValuesForNewAdvert(AdvertDto advertDto) {
        Advert advert = advertConverter.toAdvert(advertDto);
        advert.setUuid(UUID.randomUUID());
        advert.setCreateDate(LocalDateTime.now());
        advert.setUpdateDate(advert.getCreateDate());
        advert.setAdvertStatusEnum(AdvertStatusEnum.REVIEW);
        Customer customer = customerRepository.findCustomerByUuid(advertDto.getCustomerUuid());
        advert.setCustomer(customer);
        return advert;
    }

    public List<AdvertDto> filterAdverts(List<AdvertFilter> advertFilters) {
        List<Advert> allAdverts = advertRepository.findAll();
        List<AdvertDto> advertDtos = new ArrayList<>();
        for (AdvertFilter advertFilter : advertFilters) {
            allAdverts = advertFilter.filter(allAdverts);
        }
        allAdverts.forEach(advert -> advertDtos.add(advertConverter.toAdvertDto(advert)));
        return advertDtos;
    }

    public Data<List<AdvertDto>> findAllAdverts(PageRequest pageRequest) {
        List<Advert> allAdverts = advertRepository.findAll();
        List<AdvertDto> paginatedResult = new ArrayList<>();
        allAdverts.stream()
                .skip((pageRequest.getPage() - 1) * pageRequest.getSize())
                .limit(pageRequest.getSize())
                .forEach(advert -> paginatedResult.add(advertConverter.toAdvertDto(advert)));
        long total = allAdverts.size();
        long currentPageTotal = paginatedResult.size();

        Pagination pagination = new Pagination(pageRequest.getPage(), currentPageTotal, calculatePageCount(total, pageRequest.getSize()), total);
        return new Data<>(paginatedResult, pagination);
    }

    private Long calculatePageCount(Long totalSize, Long pageRequestSize) {
        Double result = totalSize.doubleValue() / pageRequestSize.doubleValue();
        Double roundedResult = Math.ceil(result);
        return roundedResult.longValue();
    }
}