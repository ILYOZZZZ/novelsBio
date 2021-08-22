package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.Role;
import com.ilyozzz.novelsbio.entity.User;
import com.ilyozzz.novelsbio.entity.enums.RoleName;
import com.ilyozzz.novelsbio.payload.*;
import com.ilyozzz.novelsbio.repository.LanguageRep;
import com.ilyozzz.novelsbio.repository.RoleRep;
import com.ilyozzz.novelsbio.repository.UserRep;
import com.ilyozzz.novelsbio.resModels.ResUserInfos;
import com.ilyozzz.novelsbio.resModels.ResUsersWithRoles;
import com.ilyozzz.novelsbio.resModels.ResUsersWithRolesPaged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class   UserService {
    @Autowired
    UserRep userRep;

    @Autowired
    LanguageRep languageRep;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    RoleRep roleRep;

    public ApiResponse saveUser (ReqSaveUser reqSaveUser){
        List<Role> allByNameIn = roleRep.findAllByNameIn(Collections.singletonList(RoleName.ROLE_USER));
        userRep.save(new User(reqSaveUser.getFirstName(),reqSaveUser.getLastName(),reqSaveUser.getUserName().toLowerCase(Locale.ROOT),passwordEncoder.encode(reqSaveUser.getPassword()),languageRep.findById(reqSaveUser.getLanguageId()).get(),allByNameIn));
        return new ApiResponse("success",true);
    }

    //xamma role boshqattan save boladi
    public ApiResponse addOrRemoveRoleFromUser(List<ReqAddRole> reqAddRole){
        for (ReqAddRole addRole : reqAddRole) {

            User user = userRep.findById(addRole.getUserId()).get();

            List<RoleName> roleNames = new ArrayList<>();

            for (String roleNameInString : addRole.getRoleName()) {
                roleNames.add(RoleName.valueOf(roleNameInString));
            }

            List<Role> newRoles = roleRep.findAllByNameIn(roleNames);

            user.setRoles(newRoles);
            userRep.save(user);
        }

        return new ApiResponse("success",true);
    }

    public ApiResponse editUserFields(ReqSaveUser reqSaveUser, UUID userId){
        User user = userRep.findById(userId).get();

        user.setUserName(reqSaveUser.getUserName().toLowerCase(Locale.ROOT));
        user.setFirstName(reqSaveUser.getFirstName());
        user.setLastName(reqSaveUser.getLastName());
        user.setPassword(passwordEncoder.encode(reqSaveUser.getPassword()));
        userRep.save(user);
        return new ApiResponse("success",true);
    }

    public boolean checkPassword (ReqCheckPassword reqCheckPassword){
        User user = userRep.findById(reqCheckPassword.getUserId()).get();

        if(user.getPassword().equals(passwordEncoder.encode(reqCheckPassword.getPassword()))){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAlreadyExists(String userName){
        Optional<User> byUserName = userRep.findByUserName(userName);

        if(byUserName.isPresent()){
            return false;
        }else{
            return true;
        }
    }

    public ApiResponse changeLangOfUser(ReqEditUserLang reqEditUserLang){
        User user = userRep.findById(reqEditUserLang.getId()).get();
        user.setLanguage(languageRep.findById(reqEditUserLang.getLangId()).get());
        userRep.save(user);
        return new ApiResponse("success",true);
    }


    //after login user infos
    public ApiResponse getAllUserInfos(User user){
        ResUserInfos currentUserInfo = userRep.getCurrentUserInfo(user.getId());
        return new ApiResponse("success", true , currentUserInfo);
    }


    //get all users to edit their role for super admin
    public ApiResponse getAllUserByRole(Integer page,  Integer size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<User> allUserPaged = userRep.getAllUserPaged(pageable);
        List<ResUsersWithRoles> usersWithRoles = new ArrayList<>();
        ResUsersWithRolesPaged resUsersWithRolesPaged = new ResUsersWithRolesPaged();
        resUsersWithRolesPaged.setTotalPages(allUserPaged.getTotalPages());
        resUsersWithRolesPaged.setTotalElements(allUserPaged.getTotalElements());
        for (User user : allUserPaged.getContent()) {
            List<Role> allRolesOfUser = roleRep.getAllRolesOfUser(user.getId());
            ResUsersWithRoles resUsersWithRoles = new ResUsersWithRoles();
            resUsersWithRoles.setRoles(allRolesOfUser);
            resUsersWithRoles.setId(user.getId());
            resUsersWithRoles.setUserName(user.getUsername());
            usersWithRoles.add(resUsersWithRoles);
        }
        resUsersWithRolesPaged.setUsersWithRolesList(usersWithRoles);
        return new ApiResponse("success",true,resUsersWithRolesPaged);
    }

    



}
