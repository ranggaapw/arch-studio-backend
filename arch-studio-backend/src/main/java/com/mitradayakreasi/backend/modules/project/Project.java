package com.mitradayakreasi.backend.modules.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatis membuatkan ID angka berurutan (1, 2, 3...)
    private Long id;

    private String judul;

    @Column(columnDefinition = "TEXT") // Menggunakan TEXT agar bisa menampung deskripsi panjang
    private String deskripsi;

    @Column(columnDefinition = "TEXT")
    private String imageUrl; // Untuk menyimpan link foto/gambar hasil desain

    @ElementCollection
    private List<String> kategori;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "location")
    private String location;

    @Column(name = "year")
    private Integer year;

    @Column(name = "materials")
    private String materials;

    @ElementCollection
    @CollectionTable(name = "project_gallery_images", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> galleryImages;

    private Boolean isRecommended = false; // Menandakan apakah project ini direkomendasikan 

    @Column(name = "budget")
    private Double budget;

    // Getter dan Setter untuk masing-masing field baru
    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public List<String> getGalleryImages() {
        return galleryImages;
    }

    public void setGalleryImages(List<String> galleryImages) {
        this.galleryImages = galleryImages;
    }
}