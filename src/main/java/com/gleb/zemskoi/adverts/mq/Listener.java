package com.gleb.zemskoi.adverts.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.service.AdvertService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Listener {

    private final AdvertService advertService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @LogJournal
    @JmsListener(destination = "${new.advert.queue}")
    public void newAdvertForReview(String msg) {
        Advert advert = objectMapper.readValue(msg, Advert.class);
        advertService.changeAdvertStatus(advert);
    }
}
