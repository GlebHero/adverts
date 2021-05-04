package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.filter.AdvertFilter;
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

    @GetMapping(value = "advertUuid/{advertUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AdvertDto> findAdvertByUuid(@PathVariable(name = "advertUuid") UUID advertUuid) {
        return new RestResponseEntity<>(advertService.findAdvertByUuid(advertUuid));
    }

    @GetMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)

    public RestResponseEntity<List<AdvertDto>> findAdvertsByCustomerUuid(@PathVariable(name = "customerUuid") UUID customerUuid,
                                                                         @RequestParam(required = false, name = "active", defaultValue = "false") Boolean active) {
        return new RestResponseEntity<>(advertService.findAdvertsByCustomerId(customerUuid, active));
    }

    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AdvertDto> saveAdvert(@Valid @RequestBody AdvertDto advert) {
        return new RestResponseEntity<>(advertService.saveAdvert(advert));
    }

    @DeleteMapping(value = "advertUuid/{advertUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void disableAdvertByUuid(@PathVariable(name = "advertUuid") UUID advertUuid) {
        advertService.disableAdvertByUuid(advertUuid);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AdvertDto> updateAdvertByUuid(@Valid @RequestBody AdvertDto advertDto) {
        return advertService.updateAdvertByUuid(advertDto);
    }

    @PostMapping(value = "filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<List<AdvertDto>> filterAdverts(@RequestBody List<AdvertFilter> advertFilters) {
        return advertService.filterAdverts(advertFilters);
    }
}
