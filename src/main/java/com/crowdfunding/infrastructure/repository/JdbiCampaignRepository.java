package com.crowdfunding.infrastructure.repository;

import com.crowdfunding.domain.entity.Campaign;
import com.crowdfunding.domain.repository.CampaignRepository;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbiCampaignRepository implements CampaignRepository {

    private final Jdbi jdbi;

    public JdbiCampaignRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Campaign save(Campaign campaign) {
        return jdbi.withHandle(handle -> {
            Long id = handle.createUpdate(
                    "INSERT INTO campaigns (user_id, title, description, goal_amount, current_amount, deadline, status) " +
                    "VALUES (:userId, :title, :description, :goalAmount, :currentAmount, :deadline, :status)")
                    .bind("userId", campaign.getUserId())
                    .bind("title", campaign.getTitle())
                    .bind("description", campaign.getDescription())
                    .bind("goalAmount", campaign.getGoalAmount())
                    .bind("currentAmount", campaign.getCurrentAmount())
                    .bind("deadline", campaign.getDeadline())
                    .bind("status", campaign.getStatus().name())
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Long.class)
                    .one();

            campaign.setId(id);
            return campaign;
        });
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM campaigns WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Campaign.class)
                        .findOne());
    }

    @Override
    public List<Campaign> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM campaigns ORDER BY created_at DESC")
                        .mapToBean(Campaign.class)
                        .list());
    }

    @Override
    public List<Campaign> findAll(int page, int size) {
        int offset = page * size;
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM campaigns ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("limit", size)
                        .bind("offset", offset)
                        .mapToBean(Campaign.class)
                        .list());
    }

    @Override
    public int countAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM campaigns")
                        .mapTo(Integer.class)
                        .one());
    }

    @Override
    public List<Campaign> findByUserId(Long userId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM campaigns WHERE user_id = :userId ORDER BY created_at DESC")
                        .bind("userId", userId)
                        .mapToBean(Campaign.class)
                        .list());
    }

    @Override
    public void update(Campaign campaign) {
        jdbi.useHandle(handle ->
                handle.createUpdate(
                        "UPDATE campaigns SET title = :title, description = :description, " +
                        "goal_amount = :goalAmount, current_amount = :currentAmount, " +
                        "deadline = :deadline, status = :status WHERE id = :id")
                        .bind("id", campaign.getId())
                        .bind("title", campaign.getTitle())
                        .bind("description", campaign.getDescription())
                        .bind("goalAmount", campaign.getGoalAmount())
                        .bind("currentAmount", campaign.getCurrentAmount())
                        .bind("deadline", campaign.getDeadline())
                        .bind("status", campaign.getStatus().name())
                        .execute());
    }

    @Override
    public void deleteById(Long id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM campaigns WHERE id = :id")
                        .bind("id", id)
                        .execute());
    }
}
