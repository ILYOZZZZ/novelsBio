package com.ilyozzz.novelsbio.controller;

import com.ilyozzz.novelsbio.entity.User;
import com.ilyozzz.novelsbio.payload.*;
import com.ilyozzz.novelsbio.security.CurrentUser;
import com.ilyozzz.novelsbio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public HttpEntity<?> saveUser(@RequestBody ReqSaveUser reqSaveUser){
        ApiResponse apiResponse = userService.saveUser(reqSaveUser);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/role")
    public HttpEntity<?> editRole(@RequestBody List<ReqAddRole> reqAddRoles){
        ApiResponse apiResponse = userService.addOrRemoveRoleFromUser(reqAddRoles);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/edit/{id}")
    public HttpEntity<?> editUserFields (@RequestBody ReqSaveUser reqSaveUser , @PathVariable UUID id){
        ApiResponse apiResponse = userService.editUserFields(reqSaveUser, id);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/check/pass")
    public HttpEntity<?> checkPassword (@RequestBody ReqCheckPassword reqCheckPassword){
        boolean b = userService.checkPassword(reqCheckPassword);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/check/username/{username}")
    public HttpEntity<?> checkUserName (@PathVariable String username){
        boolean b = userService.isAlreadyExists(username);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/edit/lang")
    public HttpEntity<?> editLangOfUser(@RequestBody ReqEditUserLang reqEditUserLang){
        ApiResponse apiResponse = userService.changeLangOfUser(reqEditUserLang);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getCurrentUser (@CurrentUser User user){
        ApiResponse allUserInfos = userService.getAllUserInfos(user);
        return ResponseEntity.ok(allUserInfos);
    }

    @GetMapping("/users")
    public HttpEntity<?> getAllUsersForSuperAdmin ( @RequestParam Integer page , @RequestParam Integer size){
        ApiResponse allUserByRole = userService.getAllUserByRole(page, size);
        return ResponseEntity.ok(allUserByRole);
    }




}
