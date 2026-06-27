package com.mitradayakreasi.backend.modules.career;

import org.springframework.stereotype.Service;

@Service
public class CareerSettingsService {

    private final CareerSettingsRepository careerSettingsRepository;

    public CareerSettingsService(CareerSettingsRepository careerSettingsRepository) {
        this.careerSettingsRepository = careerSettingsRepository;
    }

    public CareerSettings getSettings() {
        return careerSettingsRepository.findById(1L)
                .orElseGet(() -> {
                    CareerSettings defaultSettings = new CareerSettings();
                    defaultSettings.setId(1L);
                    defaultSettings.setHeroTitle("Karir di Arch Studio");
                    defaultSettings.setHeroSubtitle("Mari bergabung dengan tim kami");
                    defaultSettings.setHeroBgUrl("");
                    defaultSettings.setPotentials("[]");
                    defaultSettings.setCultures("[]");
                    return careerSettingsRepository.save(defaultSettings);
                });
    }

    public CareerSettings saveSettings(CareerSettings settings) {
        settings.setId(1L);
        return careerSettingsRepository.save(settings);
    }
}
