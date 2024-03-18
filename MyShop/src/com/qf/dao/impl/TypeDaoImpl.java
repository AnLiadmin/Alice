package com.qf.dao.impl;

import com.qf.dao.TypeDao;
import com.qf.entity.Type;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public List<Type> selectTypes() {
        String sql = "select * from type";
        try {
            return runner.query(sql,new BeanListHandler<>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
