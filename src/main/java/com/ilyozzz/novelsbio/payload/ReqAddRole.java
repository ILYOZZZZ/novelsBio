package com.ilyozzz.novelsbio.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqAddRole {
    private UUID userId;
    private List<String> roleName;

}
