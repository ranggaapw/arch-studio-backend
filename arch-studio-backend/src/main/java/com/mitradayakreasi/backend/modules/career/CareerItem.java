package com.mitradayakreasi.backend.modules.career;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerItem {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        }
    }

    public String getTitle() {
        return title;
    }

    @JsonAlias({"title", "judul"})
    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    @JsonAlias({"description", "deskripsi", "desc", "deskripsiNilai", "deskripsi_nilai"})
    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @JsonAlias({"imageUrl", "image_url", "image", "visualGambar", "visual_gambar"})
    public void setImageUrl(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
    }
}
