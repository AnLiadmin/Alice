package com.qf.service;

import com.qf.entity.User;

public interface UserService {
    User checkName(String username);

    int register(User user);

    User selectUesrByCode(String c);

    int updateStatus(int u_id);

    User login(String username, String password);
}
