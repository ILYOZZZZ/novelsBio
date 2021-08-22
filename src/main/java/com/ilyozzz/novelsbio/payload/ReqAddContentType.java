package com.ilyozzz.novelsbio.payload;

import lombok.Data;

@Data
public class ReqAddContentType {
    private String contentType;
    private Integer languageId;
}
