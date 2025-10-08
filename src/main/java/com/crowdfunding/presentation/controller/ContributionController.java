package com.crowdfunding.presentation.controller;

import com.crowdfunding.application.dto.CreateContributionDTO;
import com.crowdfunding.application.usecase.CreateContributionUseCase;
import com.crowdfunding.domain.entity.Contribution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    private final CreateContributionUseCase createContributionUseCase;

    public ContributionController(CreateContributionUseCase createContributionUseCase) {
        this.createContributionUseCase = createContributionUseCase;
    }

    @PostMapping
    public ResponseEntity<Contribution> create(@RequestBody CreateContributionDTO dto) {
        Contribution contribution = createContributionUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contribution);
    }
}
