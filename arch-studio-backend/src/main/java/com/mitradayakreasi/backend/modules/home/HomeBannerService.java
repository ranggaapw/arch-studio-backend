package com.mitradayakreasi.backend.modules.home;

import org.springframework.stereotype.Service;

@Service
public class HomeBannerService {

    private final HomeBannerRepository homeBannerRepository;

    public HomeBannerService(HomeBannerRepository homeBannerRepository) {
        this.homeBannerRepository = homeBannerRepository;
    }

    public HomeBanner getBanner() {
        return homeBannerRepository.findById(1L)
                .orElseGet(() -> {
                    HomeBanner defaultBanner = new HomeBanner();
                    defaultBanner.setId(1L);
                    defaultBanner.setTitle("Arch Studio");
                    defaultBanner.setSubtitle("Desain Arsitektur Modern");
                    defaultBanner.setBgImageUrl("");
                    return homeBannerRepository.save(defaultBanner);
                });
    }

    public HomeBanner saveBanner(HomeBanner banner) {
        banner.setId(1L);
        return homeBannerRepository.save(banner);
    }
}
