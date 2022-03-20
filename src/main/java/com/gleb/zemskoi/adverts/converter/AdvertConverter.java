package com.gleb.zemskoi.adverts.converter;

import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(imports = UUID.class)
public interface AdvertConverter {
    AdvertDto toAdvertDto(Advert advert);

    Advert toAdvert(AdvertDto advertDto);

    Advert toAdvertClone(AdvertDto advertDto, @MappingTarget Advert advert);
}
