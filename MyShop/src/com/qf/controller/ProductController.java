package com.qf.controller;

import com.qf.entity.Page;
import com.qf.entity.Product;
import com.qf.service.ProductService;
import com.qf.service.impl.ProductServiceImpl;
import com.qf.utils.StrUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Struct;
import java.util.List;

@WebServlet("/product")
public class ProductController extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    public String productShow(HttpServletRequest request,HttpServletResponse response){
        String tid = request.getParameter("tid");
        String current = request.getParameter("currentPage");
        //List<Product> list = productService.selectByTid(tid); //根据tid返回集合
        //分页展示: 封装Page的实体  将数据集合,当前页,页大小,页总数等进行封装
        Page page = productService.getPage(tid,current);
        request.setAttribute("p",page);

        return StrUtils.FORWARD+"/goodsList.jsp";
    }
    //单品展示的功能
    public String show(HttpServletRequest request,HttpServletResponse response){
        String pid = request.getParameter("pid");
        Product product = productService.selectByPid(pid); //根据pid查询到对象
        request.setAttribute("goods",product);
        return  StrUtils.FORWARD+"/goodsDetail.jsp";
    }

}
