package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Attachment;
import com.ilyozzz.novelsbio.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRep extends JpaRepository<Attachment, UUID> {

}
