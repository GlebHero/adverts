package com.gleb.zemskoi.adverts.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Value("${new.advert.queue}")
    private String queueName;

    @SneakyThrows
    @Async
    @LogJournal
    public void sendAdvertForReview(Advert advert) {
        String jsonAdvert = objectMapper.writeValueAsString(advert);
        jmsTemplate.convertAndSend(queueName, jsonAdvert);
    }


}
