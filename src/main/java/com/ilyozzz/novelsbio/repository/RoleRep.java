package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Role;
import com.ilyozzz.novelsbio.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRep extends JpaRepository<Role, UUID> {
    List<Role> findAllByNameIn(List<RoleName> name);


    @Query(value = "select r.id as id , r.name as name  from user_role join role r on user_role.role_id = r.id where user_role.user_id = :id", nativeQuery = true)
    List<Role> getAllRolesOfUser(@Param(value = "id") UUID id);

}
