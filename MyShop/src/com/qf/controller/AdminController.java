package com.qf.controller;

import com.google.gson.Gson;
import com.qf.entity.User;
import com.qf.service.AdminService;
import com.qf.service.UserService;
import com.qf.service.impl.AdminServiceImpl;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.StrUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends BaseServlet{

    private UserService userService = new UserServiceImpl();
    private AdminService adminService = new AdminServiceImpl();

    //登录功能
    public String login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = adminService.adminLogin(username,password); //登录
        if(user!=null){ //登录成功
            if(user.getU_status()==0){
                request.setAttribute("msg","未激活,请联系管理员");
            }else{

                if (user.getU_role()==1){

                    //存储登录凭证
                    request.getSession().setAttribute(StrUtils.lOG_USER,user);

                    //设置Cookie  存的值: name-password
                    String auto = request.getParameter("auto");
                    if(auto!=null) { //勾选复选框后,auto则不为null
                        Cookie cookie = new Cookie(StrUtils.AUTO_USER, username + "-" + password);
                        cookie.setMaxAge(60 * 60 * 24 * 14); //单位秒: 14天
                        response.addCookie(cookie);
                    }

                    request.getSession().setAttribute("user",user);
                    //成功,则直接跳到首页
                    request.setAttribute("admin",1);
                    return StrUtils.FORWARD+"/admin/admin.jsp";
                }
            }
        }else{
            //登录失败
            request.setAttribute("msg","用户名或密码出错");
        }
        return StrUtils.FORWARD+"/admin/login.jsp";
    }



    //查询全部
    protected String All(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = adminService.selectAll();
        return new Gson().toJson(list);
    }

    //查询单个
    protected String selectByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String sex = req.getParameter("gender");
        
        List<User> list = adminService.selectByName(username,sex);
        return new Gson().toJson(list);
    }

    //删除
    protected String delectByUid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uId = req.getParameter("u_id");
        int num =  adminService.delectByUid(uId);
        return StrUtils.REDIRECT+"/admin/userList.jsp";
    }


















}
























