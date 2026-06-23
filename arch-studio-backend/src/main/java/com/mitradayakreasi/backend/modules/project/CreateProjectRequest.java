package com.mitradayakreasi.backend.modules.project;

import lombok.Data;
import java.util.List;

@Data
public class CreateProjectRequest {
    private String judul;
    private String deskripsi;
    private String imageUrl;
    private List<String> kategori; // Untuk menampung multi-select seperti ["Interior", "Minimalis"]
    private Boolean isRecommended; // Menandakan apakah project ini direkomendasikan
}