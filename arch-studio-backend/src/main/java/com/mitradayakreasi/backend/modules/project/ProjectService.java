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

        // Simpan ke PostgreSQL
        return projectRepository.save(project);
    }

    // Fungsi untuk mengambil semua data project
    public List<Project> getAll() {
        return projectRepository.findAll();
    }
}