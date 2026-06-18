package com.mitradayakreasi.backend.modules.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String token; // wadah untuk menyimpan JWT
}
