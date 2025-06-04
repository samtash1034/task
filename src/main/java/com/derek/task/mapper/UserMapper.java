package com.derek.task.mapper;

import com.derek.task.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查詢所有的使用者
     * List 集合（多個物件放在一起的地方）
     */
    List<User> selectAll();

    /**
     * 透過帳號查詢使用者
     */
    User selectByAccountAndIdNot(Integer id, String account);


    /**
     * 新增使用者
     */
    void insert(User user);

    /**
     * 編輯使用者
     */
    void update(User user);

    /**
     * 刪除使用者
     */
    void deleteById(Integer id);

    /**
     * 透過帳號查詢使用者
     */
    User selectByAccount(String account);
}
