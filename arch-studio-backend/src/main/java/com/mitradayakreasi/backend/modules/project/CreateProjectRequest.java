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
    private String clientName;
    private String location;
    private Integer year;
    private String materials;
    private List<String> galleryImages;
    private Double budget;
}