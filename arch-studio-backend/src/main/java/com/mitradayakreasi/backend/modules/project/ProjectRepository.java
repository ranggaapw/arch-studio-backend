package com.mitradayakreasi.backend.modules.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Ubah parameter kedua dari String menjadi Long
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}