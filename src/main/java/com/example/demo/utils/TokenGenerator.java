package com.example.demo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenGenerator {
    private static final String SECRET_KEY = "dae0926a0a673cb7a4c9216f5c0b4ead196ed4770668ea5d95c5767b9ac18af3"; // 应该是一个复杂的密钥

    /**
     * @Author zhangchen
     * @Descript 生成token
     * @Date 2024-11-23
     */
    public static String createToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 3600000; // 令牌有效期1小时
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(expMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * @Author zhangchen
     * @Descript 生成随机密钥的方法
     * @Date 2024-11-23
     */
    public static String generatorSecretKey() {
        byte[] key = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            key = secretKey.getEncoded();
            log.info(bytesToHex(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToHex(key);
    }

    /**
     * @Author zhangchen
     * @Descript 密钥转16进制
     * @Date 2024-11-23
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}