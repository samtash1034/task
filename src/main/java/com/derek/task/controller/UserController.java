package com.derek.task.controller;

import ch.qos.logback.core.util.StringUtil;
import com.derek.task.entity.User;
import com.derek.task.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{ // 入口

    // 注入 userMapper 物件
    @Autowired
    private UserMapper userMapper;


    // 查詢所有使用者 API
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        // 從資料庫查出來的所有使用者
        List<User> users = userMapper.selectAll();

        // 建議 map 集合，把資料塞進去
        // key 都要是字串，用""包起來
        Map<String, Object> response = new HashMap<>();
        response.put("message", "");
        response.put("code", 200);
        response.put("result", users);

        // 回傳一個 HTTP 回應（ResponseEntity 物件），狀態碼是 200 OK，內容是 response
        // 200 = OK = 成功
        // 400 = Bad Request = 失敗
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 新增使用者
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // map 集合
        Map<String, Object> response = new HashMap<>();

        // 插入使用者到資料庫
        userMapper.insert(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 編輯使用者
     */
    @PutMapping
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
        User userByAccount = userMapper.selectByAccountAndIdNot(user.getId(), user.getAccount());
        if (userByAccount != null) {
            response.put("message", "帳號已存在");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // 編輯帳號
        userMapper.update(user);

        // 回傳成功訊息
        response.put("message", "使用者編輯成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 刪除使用者
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        // 根據id刪除使用者
        userMapper.deleteById(id);

        response.put("message", "使用者刪除成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

