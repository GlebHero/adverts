package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertStopWordService {

    @Value("#{'${list.of.stop.words}'.split(',')}")
    private List<String> stopDictionaryWord;

    @LogJournal
    public Boolean containsBadWord(Advert advert) {
        Optional<String> titleStopWord = findStopWord(advert.getTitle());
        Optional<String> descriptionStopWord = findStopWord(advert.getDescription());
        return titleStopWord.isPresent() || descriptionStopWord.isPresent();
    }

    private Optional<String> findStopWord(String text) {
        return Arrays.stream(text.split(" ")).filter(s -> stopDictionaryWord.contains(s)).findFirst();
    }
}
