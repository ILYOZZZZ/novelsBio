package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Attachment;
import com.ilyozzz.novelsbio.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentContentRep extends JpaRepository<AttachmentContent, UUID> {
    void deleteAttachmentContentByAttachment(Attachment attachment);
    AttachmentContent findByAttachment(Attachment attachment);



}
