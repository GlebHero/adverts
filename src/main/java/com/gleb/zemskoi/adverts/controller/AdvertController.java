package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.Advert;
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
    public Advert findAdvertById(@RequestParam Long id) {
        return advertService.findAdvertById(id);
    }

    @GetMapping("costumerId")
    public List<Advert> findAdvertByCustomerId(@RequestParam Long id) {
        return advertService.findAdvertByCustomerId(id);
    }

    @PostMapping("save")
    public Advert saveAdvert(@Valid @RequestBody Advert advert) {
        return advertService.saveAdvert(advert);
    }
}
