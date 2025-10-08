package com.crowdfunding.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Contribution {
    private Long id;
    private Long campaignId;
    private Long userId;
    private BigDecimal amount;
    private String message;
    private LocalDateTime createdAt;

    public Contribution() {
    }

    public Contribution(Long id, Long campaignId, Long userId, BigDecimal amount, String message, LocalDateTime createdAt) {
        this.id = id;
        this.campaignId = campaignId;
        this.userId = userId;
        this.amount = amount;
        this.message = message;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
