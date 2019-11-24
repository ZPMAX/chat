package yzp.chat.dm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yzp.chat.dm.Model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findTokenByToken(String token);
}
