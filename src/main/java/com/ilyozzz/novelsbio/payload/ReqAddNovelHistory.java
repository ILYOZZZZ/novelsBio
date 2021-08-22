package com.ilyozzz.novelsbio.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ReqAddNovelHistory {
    private UUID novelsId;
    private Integer langId;
    private String paragraph;
    private String mainText;
    private String additionalText;
    private String aboutText;
    private MultipartFile audio;
    private String readerName;


}
