package com.crowdfunding.application.usecase;

import com.crowdfunding.application.dto.PageResponse;
import com.crowdfunding.domain.entity.Campaign;
import com.crowdfunding.domain.exception.ResourceNotFoundException;
import com.crowdfunding.domain.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetCampaignUseCase {

    private final CampaignRepository campaignRepository;

    public GetCampaignUseCase(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Transactional(readOnly = true)
    public Campaign getById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Campaign> getAll() {
        return campaignRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PageResponse<Campaign> getAllPaginated(int page, int size) {
        List<Campaign> campaigns = campaignRepository.findAll(page, size);
        int totalElements = campaignRepository.countAll();
        return new PageResponse<>(campaigns, totalElements, size, page);
    }

    @Transactional(readOnly = true)
    public List<Campaign> getByUserId(Long userId) {
        return campaignRepository.findByUserId(userId);
    }
}
