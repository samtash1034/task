package com.derek.task.entity;

// 一個類 = 物件
public class User {

    private Integer id; // 屬性

    private String name; // 屬性

    private String account; // 屬性

    private String password; // 屬性

    private String role;

    // --- Getter 和 Setter 方法 ---

    // 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

