package com.derek.task.controller;

import com.derek.task.entity.User;
import com.derek.task.mapper.UserMapper;
import com.derek.task.request.LoginRequest;
import com.derek.task.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        String account = request.getAccount();
        User user = userMapper.selectByAccount(account);

        if (user == null) {
            response.put("message", "帳號不存在");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String password = request.getPassword();
        if (!password.equals(user.getPassword())) {
            response.put("message", "密碼錯誤");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 生成 JWT Token
        String token = jwtUtil.generateToken(user.getId().longValue(), user.getRole());

        result.put("token", token);
        response.put("message", "登入成功");
        response.put("code", 200);
        response.put("result", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}