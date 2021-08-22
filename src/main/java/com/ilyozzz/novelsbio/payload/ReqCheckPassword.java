package com.ilyozzz.novelsbio.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqCheckPassword {
    private UUID userId;
    private String password;
}
