package com.qf.dao;

import com.qf.entity.Cart;

import java.util.List;

public interface CartDao {
    Cart selectByUidAndPid(int uid, String pid);

    int updateCart(Cart cart);

    int insertCart(Cart cart);

    List<Cart> selectCarts(int uid);

    int deleteByCid(String cid);

    int clearCart(int u_id);
}
