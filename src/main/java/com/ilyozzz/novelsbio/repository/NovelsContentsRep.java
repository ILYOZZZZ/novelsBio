package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Novels;
import com.ilyozzz.novelsbio.entity.NovelsContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NovelsContentsRep extends JpaRepository<NovelsContents, UUID> {

}
