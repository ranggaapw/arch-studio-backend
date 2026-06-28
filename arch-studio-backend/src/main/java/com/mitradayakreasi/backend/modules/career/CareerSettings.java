package com.mitradayakreasi.backend.modules.career;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "career_settings")
public class CareerSettings {

    @Id
    private Long id;

    @Column(name = "hero_title", nullable = false)
    private String heroTitle;

    @Column(name = "hero_subtitle", nullable = false)
    private String heroSubtitle;

    @Column(name = "hero_bg_url", columnDefinition = "TEXT")
    private String heroBgUrl;

    @Convert(converter = CareerItemListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<CareerItem> potentials;

    @Convert(converter = CareerItemListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<CareerItem> cultures;
}
