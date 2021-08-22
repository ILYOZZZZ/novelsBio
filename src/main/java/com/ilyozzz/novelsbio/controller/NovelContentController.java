package com.ilyozzz.novelsbio.controller;


import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqSaveNovelContent;
import com.ilyozzz.novelsbio.service.NovelsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("api/novel/content")
public class NovelContentController {
    @Autowired
    NovelsContentService novelsContentService;

    @PostMapping("/save")
    public HttpEntity<?> saveContent(@RequestBody ReqSaveNovelContent reqSaveNovelContent) throws IOException {
        ApiResponse apiResponse = novelsContentService.saveNewContent(reqSaveNovelContent);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/get/all")
    public HttpEntity<?> getAll(@RequestParam Integer page, @RequestParam Integer size , @RequestParam Integer langId, @RequestParam UUID novelId){
        ApiResponse allContentWithHistory = novelsContentService.getAllContentWithHistory(page, size, langId, novelId);
        return ResponseEntity.ok(allContentWithHistory);
    }

    @GetMapping("/get/current")
    public HttpEntity<?> getCurrent(@RequestParam UUID contentId,  @RequestParam Integer langId){
        return ResponseEntity.ok(novelsContentService.getCurrentContent(contentId,langId));
    }





}
