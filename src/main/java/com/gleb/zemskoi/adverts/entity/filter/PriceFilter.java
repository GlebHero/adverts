package com.gleb.zemskoi.adverts.entity.filter;

import com.gleb.zemskoi.adverts.entity.db.Advert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceFilter implements AdvertFilter {
    @Digits(fraction = 2, integer = 10)
    @PositiveOrZero
    private BigDecimal minPrice;
    @Digits(fraction = 2, integer = 10)
    @PositiveOrZero
    private BigDecimal maxPrice;

    @Override
    public List<Advert> filter(List<Advert> advertList) {
        return advertList.stream()
                .filter(advert -> advert.getPrice().compareTo(minPrice)>=0)
                .filter(advert -> advert.getPrice().compareTo(maxPrice)<=0)
                .collect(Collectors.toList());
    }
}
