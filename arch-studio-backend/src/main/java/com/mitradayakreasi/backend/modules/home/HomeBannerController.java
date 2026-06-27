package com.mitradayakreasi.backend.modules.home;

import com.mitradayakreasi.backend.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home/banner")
public class HomeBannerController {

    private final HomeBannerService homeBannerService;

    public HomeBannerController(HomeBannerService homeBannerService) {
        this.homeBannerService = homeBannerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<HomeBanner>> getBanner() {
        HomeBanner banner = homeBannerService.getBanner();
        ApiResponse<HomeBanner> response = new ApiResponse<>(banner, "Berhasil mengambil banner halaman utama", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<HomeBanner>> updateBanner(@RequestBody HomeBanner banner) {
        HomeBanner updated = homeBannerService.saveBanner(banner);
        ApiResponse<HomeBanner> response = new ApiResponse<>(updated, "Banner halaman utama berhasil diupdate", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
