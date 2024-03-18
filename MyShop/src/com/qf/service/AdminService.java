package com.qf.service;

import com.qf.entity.User;

import java.util.List;

public interface AdminService {
    public User selectByAdminName(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    User adminLogin(String username, String password);

    List<User> selectAll();

    List<User> selectByName(String username, String sex);

    int delectByUid(String uId);
}
