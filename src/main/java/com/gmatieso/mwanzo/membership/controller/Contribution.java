package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.membership.dtos.ContributionBasicResponse;
import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.service.ContributionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> createContribution( @Valid @RequestBody  ContributionRequest contributionRequest) {
        ContributionBasicResponse response =  contributionService.createContribution(contributionRequest);
         return ApiResponseEntity.success("Contribution created successfully",response);
    }

    @GetMapping
    public  ResponseEntity<?> getAllContribution(Pageable pageable){
        Page<ContributionBasicResponse> responsePage =  contributionService.getAllContribution(pageable);
        return ApiResponseEntity.success("Contributions retrieved successfully", responsePage);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getContributionById(@PathVariable Long id){
         ContributionBasicResponse response =  contributionService.getContributionById(id);
        return ApiResponseEntity.success("Contribution retrieved successfully", response);
    }
}
