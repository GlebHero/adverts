package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping(value = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AdvertDto> findAdvertById(@RequestParam UUID uuid) {
        return new RestResponseEntity<>(advertService.findAdvertByUuid(uuid));
    }

    @GetMapping(value = "costumerId", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<List<AdvertDto>> findAdvertByCustomerId(@RequestParam UUID uuid) {
        return new RestResponseEntity<>(advertService.findAdvertByCustomerId(uuid));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AdvertDto> saveAdvert(@Valid @RequestBody AdvertDto advert) {
        return new RestResponseEntity<>(advertService.saveAdvert(advert));
    }
}
