package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.ContributionRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ContributionService {
   ResponseEntity<?> getAllContribution(Pageable pageable);

   ResponseEntity<?> getContributionById(Long id);

   ResponseEntity<?> createContribution(ContributionRequest request);

   ResponseEntity<?> updateContribution(Long id, ContributionRequest request);

   ResponseEntity<?> deleteContribution(Long id);
}
