package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.service.ContributionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping()
public class Contribution {
    public static final String PATH = ApiConfig.BASE_API_PATH + "contribution";

    private final ContributionService contributionService;

    public Contribution(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public ResponseEntity<?> createContribution(@RequestBody @Valid ContributionRequest contributionRequest) {
        return  contributionService.createContribution(contributionRequest);
    }
}
