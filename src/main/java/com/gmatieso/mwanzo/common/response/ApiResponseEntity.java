package com.gmatieso.mwanzo.common.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseEntity {
   public static ResponseEntity<?> success(String message) {
       ApiResponse<?> response = new ApiResponse<>(HttpStatus.OK.value(), message);
       return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<?> success(String message, T data) {
       if(data instanceof Page<?>) {
           Page<T> page = (Page<T>) data;
           PaginatedResponse<?> paginatedResponse = new PaginatedResponse<>(
                   page.getContent(),
                   page.getNumber() + 1,
                   page.getSize(),
                   page.getTotalElements(),
                   page.getTotalPages(),
                   page.isLast()
           );
           return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), message,paginatedResponse));
       }
       return  ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), message, data));

    }
}
