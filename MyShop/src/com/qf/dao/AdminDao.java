package com.qf.dao;

import com.qf.entity.User;

import java.util.List;

public interface AdminDao {
    /**
     *
     * @param username
     * @return 匹配用户,返回user对象
     */
     User selectByAdminName(String username);

    List<User> selectAll();


    List<User> selectByName(String username, String sex);

    int delectByUid(String uId);
}
