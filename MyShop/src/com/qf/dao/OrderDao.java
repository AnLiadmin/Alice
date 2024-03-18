package com.qf.dao;

import com.qf.entity.Item;
import com.qf.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    int addOrder(Orders orders);

    int insertItems(List<Item> items);

    List<Orders> selectByUid(int uid);

    Orders selectByOid(String oid);

    List<Item> selectItems(String oid);
}
