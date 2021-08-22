package com.ilyozzz.novelsbio.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;

@Data
public class ReqSaveNewNovel {

    private String firstName;
    private SimpleDateFormat birthDate;
    private MultipartFile photo;
    private String nationality;

}
