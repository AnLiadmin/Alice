package com.qf.dao.impl;

import com.qf.dao.AddrDao;
import com.qf.entity.Address;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AddrDaoImpl implements AddrDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public List<Address> selectByUid(int u_id) {
        String sql = "select * from address where u_id=?";
        try {
            return runner.query(sql,new BeanListHandler<>(Address.class),u_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addAddress(Address a) {
        String sql = "insert into address(u_id,a_name,a_phone,a_detail,a_state) " +
                "values(?,?,?,?,?)";
        try {
            return  runner.update(sql,a.getU_id(),a.getA_name(),a.getA_phone(),
                    a.getA_detail(),a.getA_state());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByAid(String aid) {
        String sql = "delete from address where a_id=?";
        try {
            return runner.update(sql,aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateAddr(Address a) {
        String sql = "update address set a_name=?,a_phone=?,a_detail=? where a_id=?";
        try {
            return runner.update(sql,a.getA_name(),a.getA_phone(),a.getA_detail(),a.getA_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int setNoDefault(int u_id) {
        String sql = "update address set a_state=0 where u_id=?";
        try {
            return runner.update(sql,u_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int setDefault(String aid) {
        String sql = "update address set a_state=1 where a_id=?";
        try {
            return runner.update(sql,aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
