package com.ilyozzz.novelsbio.controller;

import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddLanguage;
import com.ilyozzz.novelsbio.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/lang")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping
    public HttpEntity<?> getAllLanguages(){
        ApiResponse allLanguages = languageService.getAllLanguages();
        return ResponseEntity.ok(allLanguages);
    }

    @PostMapping("/save")
    public HttpEntity<?> addLanguage(@RequestBody ReqAddLanguage reqAddLanguage){
        ApiResponse apiResponse = languageService.addLanguage(reqAddLanguage);
        return ResponseEntity.ok(apiResponse);
    }

}
