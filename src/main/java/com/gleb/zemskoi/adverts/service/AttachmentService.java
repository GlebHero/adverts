package com.gleb.zemskoi.adverts.service;

import com.gleb.zemskoi.adverts.dao.AdvertRepository;
import com.gleb.zemskoi.adverts.dao.AttachmentRepository;
import com.gleb.zemskoi.adverts.entity.common.CustomerInfo;
import com.gleb.zemskoi.adverts.entity.db.Advert;
import com.gleb.zemskoi.adverts.entity.db.Attachment;
import com.gleb.zemskoi.adverts.entity.dto.AttachmentDto;
import com.gleb.zemskoi.adverts.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AdvertRepository advertRepository;
    private final CustomerInfo customerInfo;

    public AttachmentDto saveAttachment(MultipartFile file, UUID advertUUID) {
        Attachment attachment = convertMultipartToAttachment(file, advertUUID);
        attachmentRepository.save(attachment);
        return new AttachmentDto(attachment.getUuid());
    }

    @SneakyThrows
    private Attachment convertMultipartToAttachment(MultipartFile file, UUID advertUUID) {
        Advert advertByUuid = advertRepository.findAdvertByUuid(advertUUID);
        if (customerInfo.getCustomerUuid().equals(advertByUuid.getCustomer().getUuid())) {
            Attachment attachment = new Attachment();
            attachment.setAdvert(advertByUuid);
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFileType(file.getContentType());
            attachment.setData(file.getBytes());
            attachment.setUuid(UUID.randomUUID());
            return attachment;
        } else {
            throw new ForbiddenException();
        }
    }

    public Attachment downloadAttachment(UUID attachmentUuid) {
        return attachmentRepository.findAttachmentByUuid(attachmentUuid);
    }
}
