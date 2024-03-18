package com.qf.controller;

import com.qf.utils.StrUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8"); //如果编码写错,会变成下载

        String action = req.getParameter("action");
        //使用反射,可以不用多次判断
        Class clazz = this.getClass();  //获取反射对象
        try {
            Method method = clazz.getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class); //获取method对象
            Object obj = method.invoke(this,req,resp);
            if(obj!=null){
                String strRet = (String) obj;
                if(strRet.contains(":")){ //判断返回的是否有:标记
                    String str1 = strRet.split(":")[0]+":"; //redirect:/index.jsp
                    String content  = strRet.split(":")[1];
                    if(str1.equals(StrUtils.REDIRECT)){
                        resp.sendRedirect(content);  //重定向
                    }else if(str1.equals(StrUtils.FORWARD)){
                        req.getRequestDispatcher(content).forward(req,resp);
                    }else{
                        resp.getWriter().write(strRet); //返回字符串
                    }
                }else{
                    resp.getWriter().write(strRet);  //返回字符串
                }
            }else{
                //等于null,调用方法返回的是void
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
