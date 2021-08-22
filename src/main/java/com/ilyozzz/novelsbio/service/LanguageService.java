package com.ilyozzz.novelsbio.service;


import com.ilyozzz.novelsbio.entity.Languages;
import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddLanguage;
import com.ilyozzz.novelsbio.repository.LanguageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    @Autowired
    LanguageRep languageRep;


    public ApiResponse getAllLanguages(){
        return new ApiResponse("success", true,languageRep.findAll());
    }

    public ApiResponse addLanguage(ReqAddLanguage reqAddLanguage){
        languageRep.save(new Languages(reqAddLanguage.getLanguageName()));
        return new ApiResponse("success",true);
    }




}
