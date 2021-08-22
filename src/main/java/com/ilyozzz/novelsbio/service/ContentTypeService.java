package com.ilyozzz.novelsbio.service;

import com.ilyozzz.novelsbio.entity.ContentTypes;
import com.ilyozzz.novelsbio.entity.Languages;
import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddContentType;
import com.ilyozzz.novelsbio.repository.ContentTypesRep;
import com.ilyozzz.novelsbio.repository.LanguageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContentTypeService {

    @Autowired
    ContentTypesRep contentTypesRep;

    @Autowired
    LanguageRep languageRep;

    public ApiResponse addContentType(ReqAddContentType reqAddContentType){
        Languages languages = languageRep.findById(reqAddContentType.getLanguageId()).get();
        contentTypesRep.save(new ContentTypes(reqAddContentType.getContentType(),languages));
        return new ApiResponse("success",true);
    }

    public ApiResponse editContentTypeName(ReqAddContentType reqAddContentType, UUID id){
        ContentTypes contentTypes = contentTypesRep.findById(id).get();
        contentTypes.setContentType(reqAddContentType.getContentType());

        contentTypesRep.save(contentTypes);

        return new ApiResponse("success",true);
    }



}

