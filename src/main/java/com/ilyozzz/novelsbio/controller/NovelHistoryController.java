package com.ilyozzz.novelsbio.controller;


import com.ilyozzz.novelsbio.payload.ApiResponse;
import com.ilyozzz.novelsbio.payload.ReqAddNovelHistory;
import com.ilyozzz.novelsbio.service.NovelsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/history")
public class NovelHistoryController {

    @Autowired
    NovelsHistoryService novelsHistoryService;

    @PostMapping("/save")
    public HttpEntity<?> saveNovelsHistory(@RequestBody ReqAddNovelHistory reqAddNovelHistory) throws IOException {
        ApiResponse apiResponse = novelsHistoryService.saveNovelsHistory(reqAddNovelHistory);
        return ResponseEntity.ok(apiResponse);
    }



}
