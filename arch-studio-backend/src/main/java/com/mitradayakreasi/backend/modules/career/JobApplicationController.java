package com.mitradayakreasi.backend.modules.career;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobApplication>>> getAll() {
        List<JobApplication> applications = jobApplicationService.getAll();
        ApiResponse<List<JobApplication>> response = new ApiResponse<>(applications, "Berhasil mengambil seluruh berkas lamaran masuk", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JobApplication>> create(@RequestBody JobApplication jobApplication) {
        JobApplication created = jobApplicationService.create(jobApplication);
        ApiResponse<JobApplication> response = new ApiResponse<>(created, "Lamaran pekerjaan Anda berhasil dikirim", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<JobApplication>> markAsRead(@PathVariable Long id) {
        JobApplication updated = jobApplicationService.markAsRead(id);
        ApiResponse<JobApplication> response = new ApiResponse<>(updated, "Lamaran pekerjaan berhasil ditandai sebagai dibaca", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        jobApplicationService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Berkas lamaran berhasil dihapus", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
