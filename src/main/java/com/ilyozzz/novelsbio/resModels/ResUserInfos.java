package com.ilyozzz.novelsbio.resModels;

import com.ilyozzz.novelsbio.entity.Role;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface ResUserInfos {
    UUID getId();
    String getFirstName();
    String getLastName();
    String getUserName();
    String getLanguage();

    @Value("#{@roleRepository.getAllRolesOfUser(target.id)}")
    List<Role> getUserRoles();
}
