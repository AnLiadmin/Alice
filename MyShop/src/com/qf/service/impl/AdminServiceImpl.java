package com.qf.service.impl;

import com.qf.dao.AdminDao;
import com.qf.dao.impl.AdminDaoImpl;
import com.qf.entity.User;
import com.qf.service.AdminService;
import com.qf.utils.MD5Utils;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    @Override
    public User selectByAdminName(String username) {
        return adminDao.selectByAdminName(username);
    }

    @Override
    public User adminLogin(String username, String password) {

        User user = adminDao.selectByAdminName(username);
        if(user != null){
            //判断加密的密码
            if(user.getU_password().equals(MD5Utils.md5(password))){
                return  user;
            }
        }
        return null;
    }

    @Override
    public List<User> selectAll() {

        return adminDao.selectAll();
    }

    @Override
    public List<User> selectByName(String username, String sex) {
        return adminDao.selectByName(username,sex);
    }

    @Override
    public int delectByUid(String uId) {
        return adminDao.delectByUid(uId);
    }

}
