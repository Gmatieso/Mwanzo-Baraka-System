package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.security.entity.User;

public interface TokenService {
    void saveNewToken(String token, User user);

    boolean isValidToken(String token);

    void invalidateToken(String token);

    void revokeUserTokens(User user);

    boolean isTokenPresent(String token);

}
