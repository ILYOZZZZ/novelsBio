package com.ilyozzz.novelsbio.controller;

import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddContentType;
import com.ilyozzz.novelsbio.service.ContentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/content/type")
public class ContentTypeController {

    @Autowired
    ContentTypeService contentTypeService;


    @PostMapping("/save")
    public HttpEntity<?> saveContentType(@RequestBody ReqAddContentType reqAddContentType){
        ApiResponse apiResponse = contentTypeService.addContentType(reqAddContentType);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/edit/{id}")
    public HttpEntity<?> editContentTypeName(@PathVariable UUID id , @RequestBody ReqAddContentType reqAddContentType){
        ApiResponse apiResponse = contentTypeService.editContentTypeName(reqAddContentType, id);
        return ResponseEntity.ok(apiResponse);
    }
}
