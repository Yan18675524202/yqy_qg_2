package com.yqy.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Long expire = 43200000L;


   //生成JWT令牌
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(signKey, SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }


     //解析JWT令牌
    public static Claims parseJWT(String jwt){
        // 去除Bearer前缀
        jwt = jwt.replace("Bearer ", "");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signKey) // 设置用于验证签名的密钥
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims;
        } catch (JwtException e) {
            // JWT解析异常处理
            e.printStackTrace();
            return null; // 或者根据需要进行其他处理
        }
    }
}
