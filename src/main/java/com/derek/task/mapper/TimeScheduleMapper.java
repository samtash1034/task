package com.derek.task.mapper;

import com.derek.task.entity.TimeSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimeScheduleMapper {

    // 根據用戶ID查詢時間規劃列表
    List<TimeSchedule> selectByUserId(Integer userId);

    void insertFixedTimeSlots(Integer userId);

    void update(TimeSchedule timeSchedule);
}

