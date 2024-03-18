package com.qf.service.impl;

import com.qf.dao.UserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.utils.MD5Utils;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User checkName(String username) {
        return userDao.selectByName(username);
    }

    @Override
    public int register(User user) {
        return userDao.insertUser(user);

    }

    @Override
    public User selectUesrByCode(String c) {
        return userDao.selectByCode(c);
    }

    @Override
    public int updateStatus(int u_id) {
        return userDao.updateStatus(u_id);
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.selectByName(username);
        if(user != null){
            //判断加密的密码
            if(user.getU_password().equals(MD5Utils.md5(password))){
                return  user;
            }
        }
        return null;
    }
}
