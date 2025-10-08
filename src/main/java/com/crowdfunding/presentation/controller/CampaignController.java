package com.crowdfunding.presentation.controller;

import com.crowdfunding.application.dto.CreateCampaignDTO;
import com.crowdfunding.application.usecase.CreateCampaignUseCase;
import com.crowdfunding.application.usecase.GetCampaignUseCase;
import com.crowdfunding.domain.entity.Campaign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CreateCampaignUseCase createCampaignUseCase;
    private final GetCampaignUseCase getCampaignUseCase;

    public CampaignController(CreateCampaignUseCase createCampaignUseCase,
                              GetCampaignUseCase getCampaignUseCase) {
        this.createCampaignUseCase = createCampaignUseCase;
        this.getCampaignUseCase = getCampaignUseCase;
    }

    @PostMapping
    public ResponseEntity<Campaign> create(@RequestBody CreateCampaignDTO dto) {
        Campaign campaign = createCampaignUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(campaign);
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> getAll() {
        List<Campaign> campaigns = getCampaignUseCase.getAll();
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getById(@PathVariable Long id) {
        Campaign campaign = getCampaignUseCase.getById(id);
        return ResponseEntity.ok(campaign);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Campaign>> getByUserId(@PathVariable Long userId) {
        List<Campaign> campaigns = getCampaignUseCase.getByUserId(userId);
        return ResponseEntity.ok(campaigns);
    }
}
