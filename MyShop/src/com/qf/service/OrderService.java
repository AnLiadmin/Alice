package com.qf.service;

import com.qf.entity.Orders;

import java.util.List;

public interface OrderService {
    int addOrder(String aid, String sum, int u_id);

    List<Orders> selectOrdersByUid(int u_id);

    Orders showItems(String oid);
}
