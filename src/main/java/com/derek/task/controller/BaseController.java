package com.derek.task.controller;

public class BaseController {

    // 目前登入的使用者 id
    protected Integer userId;

    // 角色
    protected String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
