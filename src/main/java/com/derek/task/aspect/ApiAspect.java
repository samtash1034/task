package com.derek.task.aspect;

import com.derek.task.controller.AuthController;
import com.derek.task.controller.BaseController;
import com.derek.task.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@EnableAsync
@Component
public class ApiAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Pointcut("execution(* com.derek.task.controller.*.*(..))")
    public void controllerPoint() {
    }

    @Around("controllerPoint()")
    public Object doAroundAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String path = request.getRequestURI();

        log.info("[API] Path: {}, Method = {}", path, pjp.getSignature().getName());

        BaseController controller = (BaseController) pjp.getTarget();

        // 如果是 AuthController，直接放行（登入、註冊等不需要驗證的接口）
        if (controller instanceof AuthController) {
            return pjp.proceed();
        }

        // 從請求頭中獲取 Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("缺少 Authorization 頭或格式錯誤，Path: {}", path);
            return createErrorResponse("請提供有效的認證令牌", 401);
        }

        // 提取 Token
        String token = jwtUtil.extractTokenFromHeader(authHeader);
        if (token == null) {
            log.warn("Token 為空，Path: {}", path);
            return createErrorResponse("認證令牌格式錯誤", 401);
        }

        try {
            // 驗證 Token 是否有效
            if (!jwtUtil.validateToken(token)) {
                log.warn("Token 無效或已過期，Path: {}", path);
                return createErrorResponse("認證令牌無效或已過期", 401);
            }

            // 從 Token 中解析用戶信息
            Integer userId = jwtUtil.getUserIdFromToken(token);
            String role = jwtUtil.getRoleFromToken(token);

            if (userId == null) {
                log.warn("無法從 Token 中獲取用戶 ID，Path: {}", path);
                return createErrorResponse("無效的用戶信息", 401);
            }

            // 設置用戶信息到 Controller
            controller.setUserId(userId);
            controller.setRole(role);

            log.debug("用戶驗證成功 - UserId: {}, Role: {}, Path: {}", userId, role, path);

        } catch (RuntimeException e) {
            log.error("Token 解析失敗: {}, Path: {}", e.getMessage(), path);
            return createErrorResponse("認證失敗，請重新登入", 401);
        }

        return pjp.proceed();
    }

    /**
     * 創建錯誤回應，格式與 UserController 一致
     */
    private ResponseEntity<?> createErrorResponse(String message, int code) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("code", code);

        HttpStatus httpStatus = code == 401 ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, httpStatus);
    }
}