package com.derek.task.controller;

import com.derek.task.entity.Task;
import com.derek.task.mapper.TaskMapper;
import com.derek.task.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskMapper taskMapper;

    // 查詢所有任務
    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<TaskResponse> tasks = new ArrayList<>();
        if(Objects.equals(role, "TEACHER")) {
            tasks = taskMapper.selectAll();
        }
        if(Objects.equals(role, "STUDENT")) {
            tasks = taskMapper.selectByUserId(userId);
        }

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
        task.setUserId(userId);
        taskMapper.insert(task);

        response.put("message", "任務新增成功");
        response.put("code", 200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 更新任務
    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();

        task.setUserId(userId);
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

