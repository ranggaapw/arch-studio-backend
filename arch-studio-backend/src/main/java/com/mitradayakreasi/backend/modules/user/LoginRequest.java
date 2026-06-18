package com.mitradayakreasi.backend.modules.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
