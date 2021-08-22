package com.ilyozzz.novelsbio.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqEditUserLang {
    private UUID id;
    private Integer langId;
}
