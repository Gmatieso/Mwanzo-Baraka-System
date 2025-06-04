package com.gmatieso.mwanzo.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(int code, String message, T data) {
    public ApiResponse(int code, String message){
        this(code, message, null);
    }

}
