package com.gmatieso.mwanzo.user.service.tokenservice;

import com.gmatieso.mwanzo.common.utils.TokenType;
import com.gmatieso.mwanzo.user.entity.Token;
import com.gmatieso.mwanzo.user.entity.User;
import com.gmatieso.mwanzo.user.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public void saveNewToken(String token, User user) {
        Token newToken = new Token();
        newToken.setToken(token);
        newToken.setUser(user);
        newToken.setTokenType(TokenType.BEARER);

        tokenRepository.save(newToken);

    }

    @Override
    public boolean isValidToken(String jwtToken) {
        return tokenRepository.findByToken(jwtToken)
                .filter(token -> !token.isExpired() && !token.isInvalid())
                .isPresent();
    }

    @Override
    public void invalidateToken(String jwtToken) {
        Token token = tokenRepository.findByToken(jwtToken).orElse(null);
        if (token != null) {
            token.setExpired(true);
            token.setInvalid(true);
            tokenRepository.save(token);
        }
    }

    @Override
    public void revokeUserTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllValidTokensByUser(String.valueOf(user.getId()));
        if (validTokens.isEmpty()) {
            return;
        }
        validTokens.forEach(t -> {
            t.setInvalid(true);
            t.setExpired(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    @Override
    public boolean isTokenPresent(String token) {
        Optional<Token> tokenOptional =  tokenRepository.findByToken(token);
        return tokenOptional.isPresent();
    }
}
