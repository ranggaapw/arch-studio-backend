package com.mitradayakreasi.backend.modules.serviceitem;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services/processes")
public class WorkProcessController {

    private final WorkProcessService workProcessService;

    public WorkProcessController(WorkProcessService workProcessService) {
        this.workProcessService = workProcessService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WorkProcess>>> getAll() {
        List<WorkProcess> processes = workProcessService.getAll();
        ApiResponse<List<WorkProcess>> response = new ApiResponse<>(processes, "Berhasil mengambil seluruh daftar metode kerja", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkProcess>> create(@RequestBody WorkProcess workProcess) {
        WorkProcess created = workProcessService.create(workProcess);
        ApiResponse<WorkProcess> response = new ApiResponse<>(created, "Metode kerja baru berhasil ditambahkan", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkProcess>> update(@PathVariable Long id, @RequestBody WorkProcess request) {
        WorkProcess updated = workProcessService.update(id, request);
        ApiResponse<WorkProcess> response = new ApiResponse<>(updated, "Metode kerja berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        workProcessService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Metode kerja berhasil dihapus", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
