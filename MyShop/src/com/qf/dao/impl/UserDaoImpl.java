package com.qf.dao.impl;

import com.qf.dao.UserDao;
import com.qf.entity.User;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public User selectByName(String username) {
        String sql = "select * from user where u_name=?";
        try {
            return runner.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insertUser(User u) {
        String sql = "insert into user(u_name,u_password,u_email,u_sex,u_code,u_role,u_status) " +
                "values(?,?,?,?,?,?,?)";
        try {
            return runner.update(sql,u.getU_name(),u.getU_password(),u.getU_email(),u.getU_sex()
                    ,u.getU_code(),u.getU_role(),u.getU_status());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User selectByCode(String c) {
        String sql = "select * from user where u_code=?";
        try {
            return runner.query(sql,new BeanHandler<>(User.class),c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateStatus(int u_id) {
        String sql = "update user set u_status=1 where u_id=?";
        try {
            return runner.update(sql,u_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        User user = new UserDaoImpl().selectByName("zs");
        System.out.println(user);
    }
}
