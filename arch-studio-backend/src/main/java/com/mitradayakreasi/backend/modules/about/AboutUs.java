package com.mitradayakreasi.backend.modules.about;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "about_us")
public class AboutUs {

    @Id
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String vision;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mission;
}
