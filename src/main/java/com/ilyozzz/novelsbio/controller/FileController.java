package com.ilyozzz.novelsbio.controller;


import com.ilyozzz.novelsbio.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getFile(@PathVariable UUID id, HttpServletResponse response) {
        return attachmentService.getAttachmentContent(id, response);
    }
}
