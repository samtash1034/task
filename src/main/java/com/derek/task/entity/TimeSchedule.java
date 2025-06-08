package com.derek.task.entity;

public class TimeSchedule {

    private Integer id;           // 主鍵
    private Integer userId;       // 用戶ID
    private String startTime;       // 時間段開始時間（如：08:10）
    private String endTime;         // 時間段結束時間（如：12:00）
    private String monday;        // 星期一該時間段的活動
    private String tuesday;       // 星期二該時間段的活動
    private String wednesday;     // 星期三該時間段的活動
    private String thursday;      // 星期四該時間段的活動
    private String friday;        // 星期五該時間段的活動
    private String saturday;      // 星期六該時間段的活動
    private String sunday;        // 星期日該時間段的活動
    private String status;        // 狀態：0：未完成 1:已完成

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
