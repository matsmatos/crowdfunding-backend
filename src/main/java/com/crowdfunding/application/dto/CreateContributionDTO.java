package com.crowdfunding.application.dto;

import java.math.BigDecimal;

public class CreateContributionDTO {
    private Long campaignId;
    private Long userId;
    private BigDecimal amount;
    private String message;

    public CreateContributionDTO() {
    }

    public CreateContributionDTO(Long campaignId, Long userId, BigDecimal amount, String message) {
        this.campaignId = campaignId;
        this.userId = userId;
        this.amount = amount;
        this.message = message;
    }

    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
