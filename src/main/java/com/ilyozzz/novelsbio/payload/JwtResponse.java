package com.ilyozzz.novelsbio.payload;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
