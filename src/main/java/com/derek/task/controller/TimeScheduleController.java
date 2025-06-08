package com.derek.task.controller;

import com.derek.task.entity.TimeSchedule;
import com.derek.task.mapper.TimeScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/timeSchedule")
@RestController
public class TimeScheduleController extends BaseController{

    @Autowired
    private TimeScheduleMapper timeScheduleMapper;

    // 查詢所有時間規劃
    @GetMapping
    public ResponseEntity<?> getAllSchedules() {
        List<TimeSchedule> schedules = new ArrayList<>();

        // 如果角色是學生 => 查詢學生自己的時間規劃
        if(Objects.equals(role, "STUDENT")) {
            schedules = timeScheduleMapper.selectByUserId(userId);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "");
        response.put("code", 200);
        response.put("result", schedules);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 初始化固定時間段
    @PostMapping("/init")
    public ResponseEntity<?> initFixedTimeSlots() {
        Map<String, Object> response = new HashMap<>();

        timeScheduleMapper.insertFixedTimeSlots(userId);
        response.put("message", "時間規劃初始化成功");
        response.put("code", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 根據ID更新時間規劃
    @PutMapping
    public ResponseEntity<?> updateSchedule(@RequestBody TimeSchedule timeSchedule) {
        Map<String, Object> response = new HashMap<>();

        timeScheduleMapper.update(timeSchedule);

        response.put("message", "時間規劃更新成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
