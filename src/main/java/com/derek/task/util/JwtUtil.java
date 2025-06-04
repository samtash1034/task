package com.derek.task.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret:mySecretKey123456789012345678901234567890}")
    private String secret;

    @Value("${jwt.expiration:86400}") // 24小時，單位：秒
    private Long expiration;

    /**
     * 生成 JWT Token
     */
    public String generateToken(Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        return createToken(claims, userId.toString());
    }

    /**
     * 創建 Token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 從 Token 中獲取用戶 ID
     */
    public Integer getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Integer.class);
    }

    /**
     * 從 Token 中獲取角色
     */
    public String getRoleFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("role", String.class);
    }

    /**
     * 獲取 Token 過期時間
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 從 Token 中獲取 Claims
     */
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token 已過期: {}", e.getMessage());
            throw new RuntimeException("Token 已過期");
        } catch (UnsupportedJwtException e) {
            log.error("不支援的 Token: {}", e.getMessage());
            throw new RuntimeException("不支援的 Token");
        } catch (MalformedJwtException e) {
            log.error("Token 格式錯誤: {}", e.getMessage());
            throw new RuntimeException("Token 格式錯誤");
        } catch (SecurityException e) {
            log.error("Token 簽名錯誤: {}", e.getMessage());
            throw new RuntimeException("Token 簽名錯誤");
        } catch (IllegalArgumentException e) {
            log.error("Token 參數錯誤: {}", e.getMessage());
            throw new RuntimeException("Token 參數錯誤");
        }
    }

    /**
     * 驗證 Token 是否過期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 驗證 Token 是否有效
     */
    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            log.error("Token 驗證失敗: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 獲取簽名密鑰
     */
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 從請求頭中提取 Token
     */
    public String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
