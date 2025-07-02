package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.service.ContributionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController()
@RequestMapping(Contribution.PATH)
public class Contribution {
    public static final String PATH = ApiConfig.BASE_API_PATH + "contribution";

    private final ContributionService contributionService;

    public Contribution(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public ResponseEntity<?> createContribution(@RequestBody  ContributionRequest contributionRequest) {
        return  contributionService.createContribution(contributionRequest);
    }

    @GetMapping
    public  ResponseEntity<?> getAllContribution(Pageable pageable){
        return  contributionService.getAllContribution(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getContributionById(@PathVariable Long id){
        return  contributionService.getContributionById(id);
    }
}
