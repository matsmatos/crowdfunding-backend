package com.crowdfunding.application.usecase;

import com.crowdfunding.application.dto.CreateCampaignDTO;
import com.crowdfunding.domain.entity.Campaign;
import com.crowdfunding.domain.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCampaignUseCase {

    private final CampaignRepository campaignRepository;

    public CreateCampaignUseCase(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public Campaign execute(CreateCampaignDTO dto) {
        Campaign campaign = new Campaign();
        campaign.setUserId(dto.getUserId());
        campaign.setTitle(dto.getTitle());
        campaign.setDescription(dto.getDescription());
        campaign.setGoalAmount(dto.getGoalAmount());
        campaign.setDeadline(dto.getDeadline());

        return campaignRepository.save(campaign);
    }
}
