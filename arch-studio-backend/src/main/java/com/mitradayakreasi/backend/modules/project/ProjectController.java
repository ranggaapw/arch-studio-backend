package com.mitradayakreasi.backend.modules.project;

import com.mitradayakreasi.backend.model.WebResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173") // Membuka jalur khusus untuk React Vite
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Endpoint POST untuk menambah data
    @PostMapping
    public WebResponse<Project> create(@RequestBody CreateProjectRequest request) {
        Project project = projectService.create(request);
        return new WebResponse<>(200, "OK", project);
    }

    // Endpoint GET untuk melihat semua data
    @GetMapping
    public WebResponse<List<Project>> getAll() {
        List<Project> projects = projectService.getAll();
        return new WebResponse<>(200, "OK", projects);
    }

    // Endpoint GET untuk melihat detail satu data berdasarkan ID (Bebas akses)
    @GetMapping("/{id}")
    public WebResponse<Project> getById(@PathVariable Long id) {
        Project project = projectService.getById(id);
        return new WebResponse<>(200, "OK", project);
    }

    // Endpoint PUT untuk mengedit data (Butuh Token)
    @PutMapping("/{id}")
    public WebResponse<Project> update(@PathVariable Long id, @RequestBody UpdateProjectRequest request) {
        Project project = projectService.update(id, request);
        return new WebResponse<>(200, "OK", project);
    }

    // Endpoint DELETE untuk menghapus data (Butuh Token)
    @DeleteMapping("/{id}")
    public WebResponse<String> delete(@PathVariable Long id) {
        projectService.delete(id);
        return new WebResponse<>(200, "OK", "Project berhasil dihapus");
    }
}