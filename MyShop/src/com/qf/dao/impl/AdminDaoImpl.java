package com.qf.dao.impl;

import com.qf.dao.AddrDao;
import com.qf.dao.AdminDao;
import com.qf.entity.User;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public User selectByAdminName(String username) {

        String sql = "select * from user where u_name=?";
        try {
            return runner.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<User> selectAll() {
         String sql = "select * from user";
        try {
            return runner.query(sql,new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectByName(String username, String sex) {
        String sql = "select * from user where u_sex = ? and  u_name LIKE ? ";
        String name = "%"+username+"%";
        try {
            return runner.query(sql,new BeanListHandler<>(User.class),sex,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delectByUid(String uId) {
        String sql = "DELETE from user where u_id = ?";
        try {
            return runner.update(sql,uId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
