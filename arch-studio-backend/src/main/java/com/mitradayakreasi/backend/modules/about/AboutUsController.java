package com.mitradayakreasi.backend.modules.about;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/about")
public class AboutUsController {

    private final AboutUsService aboutUsService;

    public AboutUsController(AboutUsService aboutUsService) {
        this.aboutUsService = aboutUsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AboutUs>> getAboutUs() {
        AboutUs aboutUs = aboutUsService.getAboutUs();
        ApiResponse<AboutUs> response = new ApiResponse<>(aboutUs, "Berhasil mengambil informasi Tentang Kami", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AboutUs>> updateAboutUs(@RequestBody AboutUs aboutUs) {
        AboutUs updated = aboutUsService.saveAboutUs(aboutUs);
        ApiResponse<AboutUs> response = new ApiResponse<>(updated, "Informasi Tentang Kami berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
