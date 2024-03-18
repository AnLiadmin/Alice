package com.qf.service;

import com.qf.entity.Cart;

import java.util.List;

public interface CartService {
    int createCart(int u_id, String pid, String price);

    List<Cart> selectCarts(int uid);

    int updateCart(String cid, String price, String num);

    int delteByCid(String cid);

    int clearCart(int u_id);
}
