package com.gleb.zemskoi.adverts.entity.filter;

import com.gleb.zemskoi.adverts.entity.db.Advert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleFilter implements AdvertFilter {
    @Size(max = 100)
    private String title;

    @Override
    public List<Advert> filter(List<Advert> advertList) {
        return advertList.stream()
                .filter(advert -> advert.getTitle().contains(title))
                .collect(Collectors.toList());
    }
}
