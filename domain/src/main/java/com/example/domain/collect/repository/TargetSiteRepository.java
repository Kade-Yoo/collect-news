package com.example.domain.collect.repository;

import com.example.domain.collect.entity.TargetSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TargetSiteRepository extends JpaRepository<TargetSite, Long> {

    Optional<TargetSite> findBySite(String site);
}
