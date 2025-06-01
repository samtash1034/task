package com.project.task.controller;

import ch.qos.logback.core.util.StringUtil;
import com.project.task.entity.User;
import com.project.task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查詢所有使用者
     */
    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    /**
     * 創建使用者
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        // 驗證新增使用者欄位必填
        if(StringUtil.isNullOrEmpty(user.getAccount())) {
            response.put("message", "請輸入帳號");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(StringUtil.isNullOrEmpty(user.getName())) {
            response.put("message", "請輸入姓名");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(StringUtil.isNullOrEmpty(user.getPassword())) {
            response.put("message", "請輸入密碼");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 驗證帳號是否重複
        User userByAccount = userRepository.findByAccount(user.getAccount());
        if (userByAccount != null) {
            response.put("message", "帳號已存在");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 新增帳號
        userRepository.save(user);

        // 回傳成功訊息
        response.put("message", "使用者建立成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 編輯使用者
     */
    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        // 驗證新增使用者欄位必填
        if(StringUtil.isNullOrEmpty(user.getAccount())) {
            response.put("message", "請輸入帳號");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(StringUtil.isNullOrEmpty(user.getName())) {
            response.put("message", "請輸入姓名");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(StringUtil.isNullOrEmpty(user.getPassword())) {
            response.put("message", "請輸入密碼");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 驗證帳號是否重複
        User userByAccount = userRepository.findByAccount(user.getAccount());
        if (userByAccount != null) {
            response.put("message", "帳號已存在");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 編輯帳號
        userRepository.save(user);

        // 回傳成功訊息
        response.put("message", "使用者編輯成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 編輯使用者
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        // 根據id刪除使用者
        userRepository.deleteById(id);

        response.put("message", "使用者刪除成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

