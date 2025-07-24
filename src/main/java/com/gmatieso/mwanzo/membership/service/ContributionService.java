package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.ContributionBasicResponse;
import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import com.gmatieso.mwanzo.membership.dtos.ContributionResponse;
import com.gmatieso.mwanzo.membership.entity.Contribution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ContributionService {
   Page<ContributionResponse> getAllContribution(Pageable pageable);

   ContributionBasicResponse getContributionById(Long id);

   ContributionResponse createContribution(ContributionRequest request);

   ContributionBasicResponse updateContribution(Long id, ContributionRequest request);

   Void deleteContribution(Long id);

   Contribution getContributionByIdOrThrow(Long id);
}
