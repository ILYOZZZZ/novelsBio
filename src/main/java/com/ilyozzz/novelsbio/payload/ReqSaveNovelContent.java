package com.ilyozzz.novelsbio.payload;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ReqSaveNovelContent {
    private MultipartFile photo;
    private UUID contentTypeId;
    private UUID novelContentId;
    private Integer langId;
    private String paragraph;
    private String mainText;
    private String additionalText;
    private String aboutText;

    private MultipartFile audioOrPdfFile;
    private String readerName;



}
