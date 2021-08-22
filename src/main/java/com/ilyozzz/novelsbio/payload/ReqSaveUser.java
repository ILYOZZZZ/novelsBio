package com.ilyozzz.novelsbio.payload;

import lombok.Data;

@Data

public class ReqSaveUser {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Integer languageId;

}
