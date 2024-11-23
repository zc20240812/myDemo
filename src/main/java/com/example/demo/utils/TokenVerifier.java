package com.example.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class TokenVerifier {
    private static final String SECRET_KEY = "dae0926a0a673cb7a4c9216f5c0b4ead196ed4770668ea5d95c5767b9ac18af3"; // 应该是一个复杂的密钥

    /**
     * @Author zhangchen
     * @Descript 验证token
     * @Date 2024-11-23
     */
    public static boolean validateToken(String token, String subject) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.getSubject().equals(subject) && !isTokenExpired(claims)) {
                return true;
            }
        } catch (Exception e) {
            // 令牌验证失败
        }
        return false;
    }

    private static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
