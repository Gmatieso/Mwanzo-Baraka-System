package com.gmatieso.mwanzo.security.service;

import com.gmatieso.mwanzo.security.entity.User;

public class TokenServiceImpl implements TokenService{
    @Override
    public void saveNewToken(String token, User user) {
        Token newToken = new Token()
    }

    @Override
    public boolean isValidToken(String token) {
        return false;
    }

    @Override
    public void invalidateToken(String token) {

    }

    @Override
    public void revokeUserTokens(User user) {

    }

    @Override
    public boolean isTokenPresent(String token) {
        return false;
    }
}
