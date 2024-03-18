package com.qf.service.impl;

import com.qf.dao.CartDao;
import com.qf.dao.OrderDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Cart;
import com.qf.entity.Item;
import com.qf.entity.Orders;
import com.qf.service.CartService;
import com.qf.service.OrderService;
import com.qf.utils.RandomUtils;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public int addOrder(String aid, String sum, int uid) {
        //1.添加订单
        Orders orders = new Orders();
        orders.setO_count(new BigDecimal(sum));  //添加金额
        orders.setO_time(new Date());  //添加日期
        String oid = RandomUtils.createOrderId();  //10010
        orders.setO_id(oid);  //添加订单
        orders.setA_id(Integer.parseInt(aid));
        orders.setO_state(1);  //未支付
        orders.setU_id(uid);
        int res = orderDao.addOrder(orders);  //添加订单
        System.out.println("添加订单:"+res);

        //2.添加详情
        List<Item> items = new ArrayList<>();
        List<Cart> carts = cartDao.selectCarts(uid);
        //订单详情需要从购物车中获取数据
        for(Cart c : carts){  //循环获取,并拼成item对象
            Item i = new Item(0,oid,c.getP_id(),c.getC_count(),c.getC_num(),null);
            items.add(i);
        }
        res = orderDao.insertItems(items);  //添加订单详情集合
        System.out.println("添加详情:"+res);
        return 0;
    }

    @Override
    public List<Orders> selectOrdersByUid(int uid) {
        return orderDao.selectByUid(uid);
    }

    @Override
    public Orders showItems(String oid) {
        //1. 根据oid获取订单对象
        Orders o = orderDao.selectByOid(oid);

        //2.根据oid获取订单详情集合
        List<Item> items = orderDao.selectItems(oid);

        //3. 订单设置详情
        o.setList(items);

        return o;
    }
}
