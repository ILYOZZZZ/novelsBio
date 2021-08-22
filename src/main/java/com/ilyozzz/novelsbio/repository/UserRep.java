package com.ilyozzz.novelsbio.repository;

import com.ilyozzz.novelsbio.entity.Role;
import com.ilyozzz.novelsbio.entity.User;
import com.ilyozzz.novelsbio.resModels.ResUserInfos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRep extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);


    @Query(value = "select cast(u.id as varchar ) , u.first_name as firstName , u.last_name as lastName , u.user_name as userName , (select l.language_name from languages l where l.id=u.language_id)  as language  from users u where u.id=:id", nativeQuery = true)
    ResUserInfos getCurrentUserInfo(@Param(value="id") UUID id);



    @Query(value = "select * from users" ,nativeQuery = true)
    Page<User> getAllUserPaged(Pageable pageable);




}
