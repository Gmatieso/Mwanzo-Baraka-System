package com.gmatieso.mwanzo.common.security;

import com.gmatieso.mwanzo.user.service.tokenservice.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private String secretKey;

    private long jwtExpiration;

    private long refreshExpiration;

    private final TokenService tokenService;


}
