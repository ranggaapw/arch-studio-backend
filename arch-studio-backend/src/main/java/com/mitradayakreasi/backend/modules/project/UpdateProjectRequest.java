package com.mitradayakreasi.backend.modules.project;

import lombok.Data;
import java.util.List;

@Data
public class UpdateProjectRequest {
    private String judul;
    private String deskripsi;
    private String imageUrl;
    private List<String> kategori;
    private Boolean isRecommended; // Menandakan apakah project ini direkomendasikan
}