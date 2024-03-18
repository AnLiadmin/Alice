package com.qf.dao.impl;

import com.qf.dao.CartDao;
import com.qf.entity.Cart;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public Cart selectByUidAndPid(int uid, String pid) {
        String sql = "select * from cart where u_id=? and p_id=?";
        try {
            return runner.query(sql,new BeanHandler<>(Cart.class),uid,pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int updateCart(Cart c) {
        String sql = "update cart set c_num=?,c_count=? where c_id=?";
        try {
            return runner.update(sql,c.getC_num(),c.getC_count(),c.getC_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int insertCart(Cart c) {
        String sql = "insert into cart(u_id,p_id,c_num,c_count) values(?,?,?,?)";
        try {
            return runner.update(sql,c.getU_id(),c.getP_id(),c.getC_num(),c.getC_count());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Cart> selectCarts(int uid) {
        try {
            //连接查询: 购物车和商品表
            String sql = "select * from cart c inner join product p on c.p_id=p.p_id where c.u_id=?";
            //Map可以封装到实体中的--BeanUtils
            List<Map<String, Object>> list = runner.query(sql, new MapListHandler(),uid);
            List<Cart> carts = new ArrayList<>();
            for(Map<String, Object> map : list){
                Product product = new Product();
                BeanUtils.populate(product,map); //map注入到商品实体

                Cart cart = new Cart();
                BeanUtils.populate(cart,map);  //map注入到购物车实体

                cart.setGoods(product);  //购物车中包含商品实体
                carts.add(cart);  //将购物车对象进行存储
            }
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteByCid(String cid) {
        String sql = "delete from cart where c_id=?";
        try {
            return runner.update(sql,cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int clearCart(int u_id) {
        String sql = "delete from cart where u_id=?";
        try {
            return runner.update(sql,u_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
