package com.crowdfunding.application.usecase;

import com.crowdfunding.application.dto.CreateContributionDTO;
import com.crowdfunding.domain.entity.Campaign;
import com.crowdfunding.domain.entity.Contribution;
import com.crowdfunding.domain.exception.DomainException;
import com.crowdfunding.domain.exception.ResourceNotFoundException;
import com.crowdfunding.domain.repository.CampaignRepository;
import com.crowdfunding.domain.repository.ContributionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CreateContributionUseCase {

    private final ContributionRepository contributionRepository;
    private final CampaignRepository campaignRepository;

    public CreateContributionUseCase(ContributionRepository contributionRepository,
                                     CampaignRepository campaignRepository) {
        this.contributionRepository = contributionRepository;
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public Contribution execute(CreateContributionDTO dto) {
        Campaign campaign = campaignRepository.findById(dto.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        if (!campaign.isActive()) {
            throw new DomainException("Campaign is not active");
        }

        if (dto.getAmount().compareTo(BigDecimal.ONE) < 0) {
            throw new DomainException("Minimum contribution amount is 1.00");
        }

        Contribution contribution = new Contribution();
        contribution.setCampaignId(dto.getCampaignId());
        contribution.setUserId(dto.getUserId());
        contribution.setAmount(dto.getAmount());
        contribution.setMessage(dto.getMessage());

        Contribution saved = contributionRepository.save(contribution);

        campaign.addContribution(dto.getAmount());
        campaignRepository.update(campaign);

        return saved;
    }
}
