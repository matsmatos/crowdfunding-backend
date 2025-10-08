package com.crowdfunding.infrastructure.repository;

import com.crowdfunding.domain.entity.Contribution;
import com.crowdfunding.domain.repository.ContributionRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbiContributionRepository implements ContributionRepository {

    private final Jdbi jdbi;

    public JdbiContributionRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Contribution save(Contribution contribution) {
        return jdbi.withHandle(handle -> {
            Long id = handle.createUpdate(
                    "INSERT INTO contributions (campaign_id, user_id, amount, message) " +
                    "VALUES (:campaignId, :userId, :amount, :message)")
                    .bind("campaignId", contribution.getCampaignId())
                    .bind("userId", contribution.getUserId())
                    .bind("amount", contribution.getAmount())
                    .bind("message", contribution.getMessage())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Long.class)
                    .one();

            contribution.setId(id);
            return contribution;
        });
    }

    @Override
    public List<Contribution> findByCampaignId(Long campaignId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM contributions WHERE campaign_id = :campaignId ORDER BY created_at DESC")
                        .bind("campaignId", campaignId)
                        .mapToBean(Contribution.class)
                        .list());
    }

    @Override
    public List<Contribution> findByUserId(Long userId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM contributions WHERE user_id = :userId ORDER BY created_at DESC")
                        .bind("userId", userId)
                        .mapToBean(Contribution.class)
                        .list());
    }
}
