package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.Attachment;
import com.ilyozzz.novelsbio.entity.AttachmentContent;
import com.ilyozzz.novelsbio.exceptions.ResourceNotFound;
import com.ilyozzz.novelsbio.repository.AttachmentContentRep;
import com.ilyozzz.novelsbio.repository.AttachmentRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRep attachmentRep;

    @Autowired
    AttachmentContentRep attachmentContentRep;

    public Attachment saveAttachment(MultipartFile file) throws IOException {
        Attachment attachment = attachmentRep.save(new Attachment(file.getName(), file.getSize(), file.getContentType()));
        AttachmentContent content = attachmentContentRep.save(new AttachmentContent(file.getBytes(), attachment));
        return attachment;
    }

    public void deleteAttachment(Attachment attachment){
        attachmentContentRep.deleteAttachmentContentByAttachment(attachment);
        attachmentRep.delete(attachment);
    }

    public HttpEntity<?> getAttachmentContent(UUID attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentRep.findById(attachmentId).orElseThrow(() -> new ResourceNotFound("Attachment", "id", attachmentId));
        AttachmentContent byAttachment = attachmentContentRep.findByAttachment(attachment);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(byAttachment.getBytes());
    }
}
