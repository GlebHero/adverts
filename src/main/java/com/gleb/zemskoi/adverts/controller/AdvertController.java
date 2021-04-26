package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Advert;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping(value = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<Advert> findAdvertById(@RequestParam Long id) {
        return new RestResponseEntity<>(advertService.findAdvertById(id));
    }

    @GetMapping(value = "costumerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<List<Advert>> findAdvertByCustomerId(@RequestParam Long id) {
        return new RestResponseEntity<>(advertService.findAdvertByCustomerId(id));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<Advert> saveAdvert(@Valid @RequestBody Advert advert) {
        return new RestResponseEntity<>(advertService.saveAdvert(advert));
    }
}
