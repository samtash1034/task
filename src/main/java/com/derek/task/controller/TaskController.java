package com.derek.task.controller;

import com.derek.task.entity.Task;
import com.derek.task.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    // 查詢所有任務
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<Task> tasks = taskMapper.selectAll();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "");
        response.put("code", 200);
        response.put("result", tasks);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 新增任務
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        taskMapper.insert(task);
        response.put("message", "任務新增成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 更新任務
    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();

        if (task.getTaskName() == null || task.getTaskName().trim().isEmpty()) {
            response.put("message", "請輸入任務名稱");
            response.put("code", 400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        taskMapper.update(task);
        response.put("message", "任務更新成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 刪除任務
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        taskMapper.deleteById(id);
        response.put("message", "任務刪除成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

