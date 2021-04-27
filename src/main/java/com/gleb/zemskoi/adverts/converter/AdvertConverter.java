package com.gleb.zemskoi.adverts.converter;

import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper( imports = UUID.class )
public interface AdvertConverter {
    @Mappings({@Mapping(target="customerUuid", source = "customerUuid")})
    AdvertDto toAdvertDto(Advert advert);
    @Mappings({@Mapping(target="customerUuid", source = "customerUuid")})
    Advert toAdvert(AdvertDto advert);
}
