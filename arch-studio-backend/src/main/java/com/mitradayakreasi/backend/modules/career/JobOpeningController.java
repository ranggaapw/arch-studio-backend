package com.mitradayakreasi.backend.modules.career;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobOpeningController {

    private final JobOpeningService jobOpeningService;

    public JobOpeningController(JobOpeningService jobOpeningService) {
        this.jobOpeningService = jobOpeningService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<JobOpening>>> getAll() {
        List<JobOpening> jobs = jobOpeningService.getAll();
        ApiResponse<List<JobOpening>> response = new ApiResponse<>(jobs, "Berhasil mengambil seluruh daftar lowongan aktif", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JobOpening>> create(@RequestBody JobOpening jobOpening) {
        JobOpening created = jobOpeningService.create(jobOpening);
        ApiResponse<JobOpening> response = new ApiResponse<>(created, "Lowongan pekerjaan baru berhasil ditambahkan", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobOpening>> update(@PathVariable Long id, @RequestBody JobOpening request) {
        JobOpening updated = jobOpeningService.update(id, request);
        ApiResponse<JobOpening> response = new ApiResponse<>(updated, "Lowongan pekerjaan berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        jobOpeningService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Lowongan pekerjaan berhasil dihapus", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
