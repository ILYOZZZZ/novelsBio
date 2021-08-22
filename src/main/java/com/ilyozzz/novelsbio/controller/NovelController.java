package com.ilyozzz.novelsbio.controller;


import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqSaveNewNovel;
import com.ilyozzz.novelsbio.service.NovelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/novel")
public class NovelController {

    @Autowired
    NovelsService novelsService;

    @PostMapping("/save")
    public HttpEntity<?> saveNovel (@RequestBody ReqSaveNewNovel reqSaveNewNovel) throws IOException {
        ApiResponse apiResponse = novelsService.saveNewNovels(reqSaveNewNovel);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getDefaultRandomNovels(@RequestParam Integer page , @RequestParam Integer size){
        ApiResponse novelsDefault = novelsService.getNovelsDefault(size, page);
        return ResponseEntity.ok(novelsDefault);
    }

    @GetMapping("/filter")
    public HttpEntity<?> getFilteredNovels(@RequestParam Integer page , @RequestParam Integer size , @RequestParam String name , @RequestParam String nationality){
        ApiResponse apiResponse = novelsService.searchNovelsByNameAndFilterByNationality(page, size, name, nationality);
        return ResponseEntity.ok(apiResponse);
    }
}
