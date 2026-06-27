package com.mitradayakreasi.backend.modules.home;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "home_banners")
public class HomeBanner {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(name = "bg_image_url", columnDefinition = "TEXT")
    private String bgImageUrl;
}
