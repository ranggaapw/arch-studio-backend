package com.mitradayakreasi.backend.modules.career;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career/settings")
public class CareerSettingsController {

    private final CareerSettingsService careerSettingsService;

    public CareerSettingsController(CareerSettingsService careerSettingsService) {
        this.careerSettingsService = careerSettingsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<CareerSettings>> getSettings() {
        CareerSettings settings = careerSettingsService.getSettings();
        ApiResponse<CareerSettings> response = new ApiResponse<>(settings, "Berhasil mengambil pengaturan halaman karir", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CareerSettings>> updateSettings(@RequestBody CareerSettings settings) {
        CareerSettings updated = careerSettingsService.saveSettings(settings);
        ApiResponse<CareerSettings> response = new ApiResponse<>(updated, "Pengaturan halaman karir berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
