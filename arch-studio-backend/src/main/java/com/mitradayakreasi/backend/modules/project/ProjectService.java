package com.mitradayakreasi.backend.modules.project;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Fungsi untuk menambah project baru
    public Project create(CreateProjectRequest request) {
        Project project = new Project();
        project.setJudul(request.getJudul());
        project.setDeskripsi(request.getDeskripsi());
        project.setImageUrl(request.getImageUrl());
        project.setKategori(request.getKategori());
        project.setIsRecommended(request.getIsRecommended() != null ? request.getIsRecommended() : false);
        project.setClientName(request.getClientName());
        project.setLocation(request.getLocation());
        project.setYear(request.getYear());
        project.setMaterials(request.getMaterials());
        project.setGalleryImages(request.getGalleryImages());

        // Simpan ke PostgreSQL
        return projectRepository.save(project);
    }

    // Fungsi untuk mengambil semua data project
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    // Fungsi untuk mengambil satu project berdasarkan ID
    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Project tidak ditemukan"
                ));
    }

    // Fungsi untuk mengubah (Update) project
    public Project update(Long id, UpdateProjectRequest request) {
        // 1. Cari dulu datanya. Kalau tidak ada, fungsi getById di atas otomatis melempar error 404 Not Found
        Project project = getById(id); 
        
        // 2. Timpa data lama dengan data baru dari form
        project.setJudul(request.getJudul());
        project.setDeskripsi(request.getDeskripsi());
        project.setImageUrl(request.getImageUrl());
        project.setKategori(request.getKategori());
        project.setIsRecommended(request.getIsRecommended() != null ? request.getIsRecommended() : false);
        project.setClientName(request.getClientName());
        project.setLocation(request.getLocation());
        project.setYear(request.getYear());
        project.setMaterials(request.getMaterials());
        project.setGalleryImages(request.getGalleryImages());

        // 3. Simpan perubahannya ke PostgreSQL
        return projectRepository.save(project);
    }

    // Fungsi untuk menghapus (Delete) project
    public void delete(Long id) {
        // Cari datanya dulu, lalu hapus
        Project project = getById(id);
        projectRepository.delete(project);
    }
}