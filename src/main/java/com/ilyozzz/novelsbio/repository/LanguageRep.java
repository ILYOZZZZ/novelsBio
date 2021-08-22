package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Languages;
import com.ilyozzz.novelsbio.entity.Role;
import com.ilyozzz.novelsbio.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LanguageRep extends JpaRepository<Languages, Integer> {

}
