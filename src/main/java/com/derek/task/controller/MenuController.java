package com.derek.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseController {

    /**
     * 根據角色取得用戶菜單
     */
    @GetMapping
    public ResponseEntity<?> getMenus() {
        List<Map<String, Object>> menus = new ArrayList<>();

        if(Objects.equals(role, "TEACHER")) {
            // 老師菜單：用戶管理 + 任務管理
            Map<String, Object> userMenu = new HashMap<>();
            userMenu.put("id", "user");
            userMenu.put("name", "用戶管理");
            userMenu.put("path", "/user");
            userMenu.put("icon", "user");

            Map<String, Object> taskMenu = new HashMap<>();
            taskMenu.put("id", "task");
            taskMenu.put("name", "任務管理");
            taskMenu.put("path", "/task");
            taskMenu.put("icon", "task");

            menus.add(userMenu);
            menus.add(taskMenu);
        }

        if(Objects.equals(role, "STUDENT")) {
            // 學生菜單：只有任務管理
            Map<String, Object> taskMenu = new HashMap<>();
            taskMenu.put("id", "task");
            taskMenu.put("name", "任務管理");
            taskMenu.put("path", "/task");
            taskMenu.put("icon", "task");

            menus.add(taskMenu);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "");
        response.put("code", 200);
        response.put("result", menus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
