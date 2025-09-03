package com.gmatieso.mwanzo.user.user.service.tokenservice;

import com.gmatieso.mwanzo.user.user.entity.User;


public interface TokenService {
    void saveNewToken(String token, User user);

    boolean isValidToken(String token);

    void invalidateToken(String token);

    void revokeUserTokens(User user);

    boolean isTokenPresent(String token);

}
