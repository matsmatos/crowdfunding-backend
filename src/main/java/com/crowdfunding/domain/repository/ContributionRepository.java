package com.crowdfunding.domain.repository;

import com.crowdfunding.domain.entity.Contribution;

import java.util.List;

public interface ContributionRepository {
    Contribution save(Contribution contribution);
    List<Contribution> findByCampaignId(Long campaignId);
    List<Contribution> findByUserId(Long userId);
}
