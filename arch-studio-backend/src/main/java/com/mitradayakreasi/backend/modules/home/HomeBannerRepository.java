package com.mitradayakreasi.backend.modules.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeBannerRepository extends JpaRepository<HomeBanner, Long> {
}
