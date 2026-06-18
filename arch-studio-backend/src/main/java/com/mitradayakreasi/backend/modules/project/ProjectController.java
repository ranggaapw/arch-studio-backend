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
}