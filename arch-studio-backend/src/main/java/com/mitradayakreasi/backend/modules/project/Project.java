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

    private String imageUrl; // Untuk menyimpan link foto/gambar hasil desain

    @ElementCollection
    private List<String> kategori; 
}