package com.gleb.zemskoi.adverts.controller;

import com.gleb.zemskoi.adverts.aop.logging.LogJournal;
import com.gleb.zemskoi.adverts.entity.common.RestResponseEntity;
import com.gleb.zemskoi.adverts.entity.db.Attachment;
import com.gleb.zemskoi.adverts.entity.dto.AttachmentDto;
import com.gleb.zemskoi.adverts.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Operation(summary = "Save attachment")
    @LogJournal
    @PostMapping(value = "save/{advertUuid}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponseEntity<AttachmentDto> saveAttachment(@RequestParam(name = "attachment") MultipartFile file, @PathVariable(name = "advertUuid") UUID advertUuid) {
        return new RestResponseEntity<>(attachmentService.saveAttachment(file, advertUuid));
    }

    @Operation(summary = "Download attachment")
    @LogJournal
    @GetMapping(value = "{attachmentUuid}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable(name = "attachmentUuid") UUID attachmentUuid) {
        Attachment attachment = attachmentService.downloadAttachment(attachmentUuid);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

}
