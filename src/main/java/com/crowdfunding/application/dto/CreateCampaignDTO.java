package com.crowdfunding.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateCampaignDTO {
    private Long userId;
    private String title;
    private String description;
    private BigDecimal goalAmount;
    private LocalDate deadline;

    public CreateCampaignDTO() {
    }

    public CreateCampaignDTO(Long userId, String title, String description, BigDecimal goalAmount, LocalDate deadline) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.goalAmount = goalAmount;
        this.deadline = deadline;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getGoalAmount() { return goalAmount; }
    public void setGoalAmount(BigDecimal goalAmount) { this.goalAmount = goalAmount; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}
