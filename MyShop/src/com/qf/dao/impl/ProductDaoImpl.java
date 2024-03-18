package com.qf.dao.impl;

import com.qf.dao.ProductDao;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public List<Product> selectByTid(String tid) {
        String sql = "select * from product where t_id=?";
        try {
            return  runner.query(sql,new BeanListHandler<>(Product.class),tid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getTotalCount(String tid) {
        String sql = "select count(*) from product where t_id=?";
        try {
            long res = (long) runner.query(sql,new ScalarHandler(),tid);
            return  res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Product> selectByPage(String tid, int startIndex, int pageSize) {
        String sql = "select * from product where t_id=? limit ?,?";
        try {
            return  runner.query(sql,new BeanListHandler<>(Product.class),tid,startIndex,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product selectByPid(String pid) {
        String sql = "select * from product where p_id=?";
        try {
            return runner.query(sql,new BeanHandler<>(Product.class),pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
