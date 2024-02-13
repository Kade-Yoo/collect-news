package com.example.domain.collect.service;

import com.example.domain.collect.entity.TargetSite;
import com.example.domain.collect.repository.TargetSiteRepository;
import com.example.domain.common.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.domain.common.ErrorCode.ALREADY_PRESENT_SITE_ERROR;

@Service
@RequiredArgsConstructor
public class TargetSiteService {

    private final TargetSiteRepository repository;

    public void save(String site) {
        Optional<TargetSite> bySite = repository.findBySite(site);
        if (bySite.isEmpty()) {
            TargetSite targetSite = new TargetSite(site);
            repository.save(targetSite);
        } else {
            throw new BusinessException(ALREADY_PRESENT_SITE_ERROR);
        }
    }

    public List<String> findAllTargetSite() {
        return repository.findAll().stream().map(TargetSite::getSite).toList();
    }
    public TargetSite getBySite(String site) {
        return repository.findBySite(site).orElseThrow();
    }
}
