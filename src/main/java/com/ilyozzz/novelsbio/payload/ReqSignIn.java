package com.ilyozzz.novelsbio.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqSignIn {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
