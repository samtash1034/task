package com.derek.task.controller;

import com.derek.task.entity.User;
import com.derek.task.mapper.UserMapper;
import com.derek.task.request.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        String account = request.getAccount();
        User user = userMapper.selectByAccount(account);

        if(user == null){
            response.put("message", "帳號不存在");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String password = request.getPassword();
        if(!password.equals(user.getPassword())){
            response.put("message", "密碼錯誤");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getAccount());
        session.setAttribute("userName", user.getName());

        result.put("id", user.getId());
        result.put("email", user.getAccount());
        result.put("name", user.getName());

        response.put("message", "登入成功");
        response.put("code", 200);
        response.put("result", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
