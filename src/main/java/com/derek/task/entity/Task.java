package com.derek.task.entity;


import java.time.LocalDate;

public class Task {

    private Integer id; // 屬性

    private String taskName;

    private Integer status = 0;

    private LocalDate dueDate;

    private Integer userId;

    // --- Getter 和 Setter 方法 ---

    // Integer：回傳的資料類型
    public Integer getId() {
        return id;
    } // 方法

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
