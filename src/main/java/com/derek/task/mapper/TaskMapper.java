package com.derek.task.mapper;

import com.derek.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {

    List<Task> selectAll();

    void insert(Task task);

    void update(Task task);

    void deleteById(Integer id);
}
