package com.qf.dao;

import com.qf.entity.User;

public interface UserDao {
    public User selectByName(String username); //匹配用户,返回user对象

    int insertUser(User user);

    User selectByCode(String c);

    int updateStatus(int u_id);
}
