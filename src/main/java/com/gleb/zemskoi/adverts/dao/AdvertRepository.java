package com.gleb.zemskoi.adverts.dao;

import com.gleb.zemskoi.adverts.aop.NotNullResult;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

    List<Advert> findAdvertByCustomerUuid(UUID uuid);
    @NotNullResult
    Advert findAdvertByUuid(UUID uuid);

}
