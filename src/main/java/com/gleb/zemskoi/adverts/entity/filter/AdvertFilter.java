package com.gleb.zemskoi.adverts.entity.filter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gleb.zemskoi.adverts.entity.db.Advert;

import java.util.List;

@FunctionalInterface
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PriceFilter.class, name = "price"),
        @JsonSubTypes.Type(value = TitleFilter.class, name = "title")
})
public interface AdvertFilter {
    List<Advert> filter(List<Advert> advertList);
}
