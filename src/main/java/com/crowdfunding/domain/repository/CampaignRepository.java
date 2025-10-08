package com.crowdfunding.domain.repository;

import com.crowdfunding.domain.entity.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository {
    Campaign save(Campaign campaign);
    Optional<Campaign> findById(Long id);
    List<Campaign> findAll();
    List<Campaign> findByUserId(Long userId);
    void update(Campaign campaign);
    void deleteById(Long id);
}
