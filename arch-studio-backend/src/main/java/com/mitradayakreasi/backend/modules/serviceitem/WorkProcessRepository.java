package com.mitradayakreasi.backend.modules.serviceitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkProcessRepository extends JpaRepository<WorkProcess, Long> {
}
