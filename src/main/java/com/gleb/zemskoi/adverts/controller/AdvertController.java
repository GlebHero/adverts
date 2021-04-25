package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Advert;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.service.AdvertService;
import com.gleb.zemskoi.adverts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;
    private final CustomerService customerService;

    @GetMapping("id")
    public RestResponseEntity<Advert> findAdvertById(@RequestParam Long id) {
        return new RestResponseEntity<>(advertService.findAdvertById(id));
    }

    @GetMapping("costumerId")
    public RestResponseEntity<List<Advert>> findAdvertByCustomerId(@RequestParam Long id) {
        return new RestResponseEntity<>(advertService.findAdvertByCustomerId(id));
    }

    @PostMapping("save")
    public RestResponseEntity<Advert> saveAdvert(@Valid @RequestBody Advert advert) {
        return new RestResponseEntity<>(advertService.saveAdvert(advert));
    }
}
