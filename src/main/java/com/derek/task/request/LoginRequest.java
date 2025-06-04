package com.derek.task.request;

public class LoginRequest {

    private String account; // 帳號

    private String password; // 密碼

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
}
