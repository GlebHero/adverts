package com.gleb.zemskoi.adverts.dao;

import com.gleb.zemskoi.adverts.entity.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

    List<Advert> findAdvertByCustomerId(Long id);
    Advert findAdvertById(Long id);

}
