package com.mitradayakreasi.backend.modules.about;

import org.springframework.stereotype.Service;

@Service
public class AboutUsService {

    private final AboutUsRepository aboutUsRepository;

    public AboutUsService(AboutUsRepository aboutUsRepository) {
        this.aboutUsRepository = aboutUsRepository;
    }

    public AboutUs getAboutUs() {
        return aboutUsRepository.findById(1L)
                .orElseGet(() -> {
                    AboutUs defaultAbout = new AboutUs();
                    defaultAbout.setId(1L);
                    defaultAbout.setDescription("Tentang Arch Studio");
                    defaultAbout.setVision("Visi Arch Studio");
                    defaultAbout.setMission("Misi Arch Studio");
                    return aboutUsRepository.save(defaultAbout);
                });
    }

    public AboutUs saveAboutUs(AboutUs aboutUs) {
        aboutUs.setId(1L);
        return aboutUsRepository.save(aboutUs);
    }
}
