package com.qf.service.impl;

import com.qf.dao.CartDao;
import com.qf.dao.impl.CartDaoImpl;
import com.qf.entity.Cart;
import com.qf.service.CartService;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDao cartDao = new CartDaoImpl();
    @Override
    public int createCart(int uid, String pid, String price) {
        //1.根据uid和pid返回商品记录; 判断是否存在
        Cart cart = cartDao.selectByUidAndPid(uid,pid);
        if (cart != null) {
            //2.如果存在,则进行修改数量和小计
            int num = cart.getC_num()+1;
            cart.setC_num(num);  //数量+1
            BigDecimal pp = new BigDecimal(price);
            cart.setC_count(pp.multiply(new BigDecimal(num+""))); //修改小计
            int res = cartDao.updateCart(cart);  //修改购物车
            System.out.println("修改:"+res);
        }else{
            //3.如果不存在,则进行添加一条记录
            cart = new Cart(0,uid,Integer.parseInt(pid),new BigDecimal(price),1,null);
            int res = cartDao.insertCart(cart);  //添加购物车
            System.out.println("添加:"+res);
        }
        return 0;
    }

    @Override
    public List<Cart> selectCarts(int uid) {
        return cartDao.selectCarts(uid);
    }

    @Override
    public int updateCart(String cid, String price, String num) {
        Cart cart = new Cart();
        cart.setC_id(Integer.parseInt(cid));
        cart.setC_num(Integer.parseInt(num));
        BigDecimal bb = new BigDecimal(price);
        cart.setC_count(bb.multiply(new BigDecimal(num)));
        return cartDao.updateCart(cart);
    }

    @Override
    public int delteByCid(String cid) {
        return cartDao.deleteByCid(cid);
    }

    @Override
    public int clearCart(int u_id) {
        return cartDao.clearCart(u_id);
    }
}
