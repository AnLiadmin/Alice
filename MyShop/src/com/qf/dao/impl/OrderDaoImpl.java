package com.qf.dao.impl;

import com.qf.dao.OrderDao;
import com.qf.entity.Address;
import com.qf.entity.Item;
import com.qf.entity.Orders;
import com.qf.entity.Product;
import com.qf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
    @Override
    public int addOrder(Orders o) {

        try {
            String sql = "insert into orders(o_id,u_id,a_id,o_count,o_time,o_state)" +
                    "values(?,?,?,?,?,?)";
            return  runner.update(sql,o.getO_id(),o.getU_id(),o.getA_id(),
                    o.getO_count(),o.getO_time(),o.getO_state());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertItems(List<Item> items) {
        try {
            String sql = "insert into item(o_id,p_id,i_count,i_num) values(?,?,?,?)";
            //参数1:sql  参数2:二维数组  行长度表示条数,列长度,字段数
            //二维数组中的一维数组元素,就表示一条表记录
            Object[][] obs = new Object[items.size()][];
            for(int i=0;i<items.size();i++){
                Item ii = items.get(i);  //获取item对象
                //参数顺序与添加字段的顺序一致
                obs[i] = new Object[]{ii.getO_id(),ii.getP_id(),ii.getI_count(),ii.getI_num()};
            }
            int[] arr = runner.batch(sql, obs);
            return arr.length;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Orders> selectByUid(int uid) {
        //关联查询--订单表,地址表
        String sql = "select * from orders o inner join address a on o.a_id=a.a_id and o.u_id=?";
        try {
            List<Map<String, Object>> list = runner.query(sql, new MapListHandler(), uid);
            List<Orders> orderList = new ArrayList<>();
            for(Map<String,Object> map : list){
                Orders o = new Orders();
                BeanUtils.populate(o,map);

                Address a = new Address();
                BeanUtils.populate(a,map);

                o.setAddr(a);
                orderList.add(o);
            }
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orders selectByOid(String oid) {
        //关联查询--订单表,地址表
        String sql = "select * from orders o inner join address a on o.a_id=a.a_id and o.o_id=?";
        try {
            Map<String, Object> map = runner.query(sql, new MapHandler(), oid);

            Orders o = new Orders();
            BeanUtils.populate(o,map);

            Address a = new Address();
            BeanUtils.populate(a,map);

            o.setAddr(a);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> selectItems(String oid) {
        //关联查询: 详情表与商品表
        String sql = "select * from item i inner join product p on i.p_id=p.p_id and i.o_id=?";
        try {
            List<Map<String, Object>> list = runner.query(sql, new MapListHandler(), oid);
            List<Item> items = new ArrayList<>();
            for(Map<String, Object> map : list){
                Item i = new Item();
                BeanUtils.populate(i,map);

                Product p = new Product();
                BeanUtils.populate(p,map);
                i.setGoods(p);
                items.add(i);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
