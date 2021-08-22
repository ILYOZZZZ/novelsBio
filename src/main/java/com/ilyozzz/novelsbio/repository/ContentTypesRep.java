package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.AttachmentContent;
import com.ilyozzz.novelsbio.entity.ContentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentTypesRep extends JpaRepository<ContentTypes, UUID> {

}
