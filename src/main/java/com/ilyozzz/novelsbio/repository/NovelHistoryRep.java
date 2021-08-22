package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.ContentTypes;
import com.ilyozzz.novelsbio.entity.NovelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NovelHistoryRep extends JpaRepository<NovelHistory, UUID> {

}
