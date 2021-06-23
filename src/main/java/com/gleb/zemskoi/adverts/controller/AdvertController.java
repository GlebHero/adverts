package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.common.Data;
import com.gleb.zemskoi.adverts.entity.common.PageRequest;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.dto.AdvertDto;
import com.gleb.zemskoi.adverts.entity.filter.AdvertFilter;
import com.gleb.zemskoi.adverts.service.AdvertService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Find advert by advert UUID")
    @LogJournal
    @GetMapping(value = "advertUuid/{advertUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.findAdvertByUuid.call.latency")
    @Counted(value = "adverts.findAdvertByUuid.call.cnt")
    public RestResponseEntity<AdvertDto> findAdvertByUuid(@PathVariable(name = "advertUuid") UUID advertUuid) {
        return new RestResponseEntity<>(advertService.findAdvertByUuid(advertUuid));
    }

    @Operation(summary = "Find advert by customer UUID")
    @LogJournal
    @GetMapping(value = "customerUuid/{customerUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.findAdvertsByCustomerUuid.call.latency")
    @Counted(value = "adverts.findAdvertsByCustomerUuid.call.cnt")
    public RestResponseEntity<List<AdvertDto>> findAdvertsByCustomerUuid(@PathVariable(name = "customerUuid") UUID customerUuid,
                                                                         @RequestParam(required = false, name = "active", defaultValue = "false") Boolean active) {
        return new RestResponseEntity<>(advertService.findAdvertsByCustomerId(customerUuid, active));
    }

    @Operation(summary = "Save advert")
    @LogJournal
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.saveAdvert.call.latency")
    @Counted(value = "adverts.saveAdvert.call.cnt")
    public RestResponseEntity<AdvertDto> saveAdvert(@Valid @RequestBody AdvertDto advert) {
        return new RestResponseEntity<>(advertService.saveAdvert(advert));
    }

    @Operation(summary = "Close advert by advert UUID")
    @LogJournal
    @DeleteMapping(value = "advertUuid/{advertUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.closeAdvertByUuid.call.latency")
    @Counted(value = "adverts.closeAdvertByUuid.call.cnt")
    public void closeAdvertByUuid(@PathVariable(name = "advertUuid") UUID advertUuid) {
        advertService.closeAdvertByUuid(advertUuid);
    }

    @Operation(summary = "Update advert by advert UUID")
    @LogJournal
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.updateAdvertByUuid.call.latency")
    @Counted(value = "adverts.updateAdvertByUuid.call.cnt")
    public RestResponseEntity<AdvertDto> updateAdvertByUuid(@Valid @RequestBody AdvertDto advertDto) {
        return new RestResponseEntity<>(advertService.updateAdvertByUuid(advertDto));
    }

    @Operation(summary = "Filter advert")
    @LogJournal
    @PostMapping(value = "filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.filterAdverts.call.latency")
    @Counted(value = "adverts.filterAdverts.call.cnt")
    public RestResponseEntity<List<AdvertDto>> filterAdverts(@RequestBody List<AdvertFilter> advertFilters) {
        return new RestResponseEntity<>(advertService.filterAdverts(advertFilters));
    }

    @Operation(summary = "Find all advert with pagination")
    @LogJournal
    @PostMapping(value = "all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(value = "adverts.findAllAdverts.call.latency")
    @Counted(value = "adverts.findAllAdverts.call.cnt")
    public RestResponseEntity<List<AdvertDto>> findAllAdverts(@Valid @RequestBody PageRequest pageRequest) {
        Data<List<AdvertDto>> allAdverts = advertService.findAllAdverts(pageRequest);
        return new RestResponseEntity<>(allAdverts);
    }
}
