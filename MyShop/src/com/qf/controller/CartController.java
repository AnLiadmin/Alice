package com.qf.controller;

import com.qf.entity.Cart;
import com.qf.entity.User;
import com.qf.service.CartService;
import com.qf.service.impl.CartServiceImpl;
import com.qf.utils.StrUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/cart")
public class CartController extends BaseServlet {
    private CartService cartService = new CartServiceImpl();
    public String addCart(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        String price = request.getParameter("price");
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        if(u==null){
            request.setAttribute("msg","加入购物车前,需要登录!");
            return StrUtils.FORWARD+"/login.jsp";
        }
        int res = cartService.createCart(u.getU_id(),pid,price); //创建购物车
        return StrUtils.REDIRECT+"/cartSuccess.jsp";
    }

    //展示购物车
    public String showCart(HttpServletRequest request, HttpServletResponse response){
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        if(u==null){
            request.setAttribute("msg","加入购物车前,需要登录!");
            return StrUtils.FORWARD+"/login.jsp";
        }
        List<Cart> list = cartService.selectCarts(u.getU_id());  //查询购物车
        request.setAttribute("carts",list);
        return StrUtils.FORWARD+"/cart.jsp";
    }
    //购物车修改
    public String update(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        String price = request.getParameter("price");
        String num = request.getParameter("num");
        int res = cartService.updateCart(cid,price,num);  //修改购物车
        System.out.println("修改:"+res);
        return StrUtils.REDIRECT+"/cart?action=showCart";  //跳转到展示控制层
    }

    //删除购物车
    public String deleteCart(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        int res = cartService.delteByCid(cid); //根据cid删除
        System.out.println("删除:"+res);
        return StrUtils.REDIRECT+"/cart?action=showCart";  //跳转到展示控制层
    }

    //清空购物车
    public String clearCart(HttpServletRequest request, HttpServletResponse response){
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        int res = cartService.clearCart(u.getU_id());  //清空购物车
        System.out.println("删除:"+res);
        return StrUtils.REDIRECT+"/cart?action=showCart";  //跳转到展示控制层
    }


}
