package com.gleb.zemskoi.adverts.dao;

import com.gleb.zemskoi.adverts.aop.nullchecker.NotNullResult;
import com.gleb.zemskoi.adverts.entity.db.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

    @NotNullResult
    Attachment findAttachmentByUuid(UUID uuid);

    List<Attachment> findAllByAdvertUuid(UUID uuid);
}
