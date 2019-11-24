package yzp.chat.dm.Servlet;


import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.Token;
import yzp.chat.dm.repository.TokenRepository;

import javax.annotation.Resource;

@Service
public class TokenService {
    @Resource
    TokenRepository tokenRepository;

    public Token find(String token){
        Token tokenByToken = tokenRepository.findTokenByToken(token);
        return tokenByToken;
    }
}
