package com.mitradayakreasi.backend.modules.career;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerSettingsRepository extends JpaRepository<CareerSettings, Long> {
}
