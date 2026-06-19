package com.mitradayakreasi.backend.modules.contact;

import lombok.Data;

@Data
public class CreateContactRequest {
    private String nama;
    private String email;
    private String pesan;
}