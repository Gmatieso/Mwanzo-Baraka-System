package com.gmatieso.mwanzo.user.user.auth.services;

import com.gmatieso.mwanzo.user.user.auth.dtos.AdminSignInRequest;
import com.gmatieso.mwanzo.user.user.auth.dtos.AuthTokenDto;
import com.gmatieso.mwanzo.user.user.auth.dtos.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

   ResponseEntity<?> login(@Valid LoginRequest loginRequest);

   ResponseEntity<?> checkPhone(String phone);

   AuthTokenDto signinAdmin(AdminSignInRequest adminSignInRequest);

   void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;
}
