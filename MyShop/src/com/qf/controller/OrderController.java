package com.qf.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.qf.entity.Address;
import com.qf.entity.Cart;
import com.qf.entity.Orders;
import com.qf.entity.User;
import com.qf.service.AddrService;
import com.qf.service.CartService;
import com.qf.service.OrderService;
import com.qf.service.impl.AddrServiceImpl;
import com.qf.service.impl.CartServiceImpl;
import com.qf.service.impl.OrderServiceImpl;
import com.qf.utils.StrUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/order")
public class OrderController extends  BaseServlet{
    private CartService cartService = new CartServiceImpl();  //购物车业务
    private AddrService addrService = new AddrServiceImpl();  //地址业务
    private OrderService orderService = new OrderServiceImpl();  //订单业务
    public String preview(HttpServletRequest request, HttpServletResponse response){
        //根据返回页面order.jsp可知,需要准备购物车集合与地址集合
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        List<Cart> list = cartService.selectCarts(u.getU_id());

        List<Address> addresses = addrService.selectByUid(u.getU_id()); //获取地址集合

        request.setAttribute("cartList",list);
        request.setAttribute("addList",addresses);
        return StrUtils.FORWARD+"/order.jsp";
    }

    public String addOrder(HttpServletRequest request, HttpServletResponse response){
        String aid = request.getParameter("aid");
        String sum = request.getParameter("sum");
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        int res = orderService.addOrder(aid,sum,u.getU_id());  //添加订单
        return StrUtils.REDIRECT+"/order?action=orderShow";
    }

    public String orderShow(HttpServletRequest request, HttpServletResponse response){
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        List<Orders> list = orderService.selectOrdersByUid(u.getU_id());
        request.setAttribute("orderList",list);
        return StrUtils.FORWARD+"/orderList.jsp";
    }

    public String showItems(HttpServletRequest request, HttpServletResponse response){
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        String oid = request.getParameter("oid");
        Orders orders = orderService.showItems(oid); //根据oid得到订单对象
        request.setAttribute("od",orders);
        return StrUtils.FORWARD+"/orderDetail.jsp";
    }

}
