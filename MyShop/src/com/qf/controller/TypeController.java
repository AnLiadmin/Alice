package com.qf.controller;

import com.google.gson.Gson;
import com.qf.entity.Type;
import com.qf.service.TypeService;
import com.qf.service.impl.TypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/type")
public class TypeController extends BaseServlet {
    private TypeService typeService = new TypeServiceImpl();
    public String typeShow(HttpServletRequest request, HttpServletResponse response){
        List<Type> list = typeService.selectTypes(); //查询到类别集合
        //System.out.println("类别数据:"+list);
        return new Gson().toJson(list);  //对象转json数据,并返回
    }
}
