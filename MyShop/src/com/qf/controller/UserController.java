package com.qf.controller;

import cn.dsna.util.images.ValidateCode;
import com.qf.entity.User;
import com.qf.service.UserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.*;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Struct;
import java.util.Map;

@WebServlet("/user")
public class UserController extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    public String checkUser(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        User user = userService.checkName(username); //校验用户名
        if(user==null){  //返回恭喜可用
            return "0";   //0表示可用
        }else{
            return "1";   //1表示已注册
        }
    }

    public String register(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //可以将客户端参数名与值注入到Map集合中匹配键值对
        //{u_name=ls,u_password=1,u_email=eadolhp@163.com...}
        Map<String, String[]> map = request.getParameterMap();
        //需要将Map集合的数据注入到User实体中---BeanUtils工具类(反射)
        //注意: Map的属性名要与实体属性名一致,才能注入值
        User user = new User();
        BeanUtils.populate(user,map); //map的数据注入到user实体

        user.setU_password(MD5Utils.md5(user.getU_password())); //密码加密
        user.setU_code(RandomUtils.createActive());  //生成激活码-日期拼接
        user.setU_role(0); //0-普通会员,1-管理员
        user.setU_status(0); //激活状态: 0-未激活  1-已激活
        int res = userService.register(user); //注册功能
        System.out.println("注册:"+res);
        if(res>0){
            EmailUtils.sendEmail(user);  //发送邮件
            return StrUtils.REDIRECT+"/registerSuccess.jsp";
        }else{
            request.setAttribute("msg","注册失败");
            return StrUtils.FORWARD+"/message.jsp";
        }
    }

    //激活邮件
    public String active(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        String c = request.getParameter("c");
        c = Base64Utils.decode(c); //进行解码,得到完整的激活码
        User user = userService.selectUesrByCode(c); //根据激活码对象
        if(user!=null){  //根据激活码可以返回用户对象
            if(user.getU_status()==0){ //没有激活过,可以激活
                //修改激活状态: 0-1
                int res = userService.updateStatus(user.getU_id());
                System.out.println("修改激活状态: "+res);

                request.setAttribute("msg","激活成功!");
            }else if(user.getU_status()==1){//已经激活,不要重复激活
                request.setAttribute("msg","已经激活,不要重复激活");
            }
        }else{ //没有找到激活记录
            request.setAttribute("msg","激活失败!");
        }
        return StrUtils.FORWARD+"/message.jsp";
    }

    //验证码展示
    public void validate(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //参数1:宽  参数2:高  参数3:验证码个数  参数4:干扰线数
        ValidateCode validateCode = new ValidateCode(110, 55, 2, 6);
        request.getSession().setAttribute("imgCode",validateCode.getCode());
        validateCode.write(response.getOutputStream());
    }

    //验证码校验
    public String checkCode(HttpServletRequest request,HttpServletResponse response){
        String code = request.getParameter("code"); //文本输入框的验证码
        //得到图片的验证码
        String imgCode = (String) request.getSession().getAttribute("imgCode");
        if(code.equalsIgnoreCase(imgCode)){
            return "0";  //0表示可用
        }else{
            return "1";  //不可用
        }
    }

    //登录功能
    public String login(HttpServletRequest request,HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username,password); //登录
        if(user!=null){ //登录成功
            if(user.getU_status()==0){
                request.setAttribute("msg","未激活,请联系管理员");
            }else{
                //成功,则直接跳到首页
                //存储登录凭证
                request.getSession().setAttribute(StrUtils.lOG_USER,user);

                //设置Cookie  存的值: name-password
                String auto = request.getParameter("auto");
                if(auto!=null) { //勾选复选框后,auto则不为null
                    Cookie cookie = new Cookie(StrUtils.AUTO_USER, username + "-" + password);
                    cookie.setMaxAge(60 * 60 * 24 * 14); //单位秒: 14天
                    response.addCookie(cookie);
                }
                return StrUtils.REDIRECT+"/index.jsp";
            }
        }else{  //登录失败
            request.setAttribute("msg","用户名或密码出错");
        }
        return StrUtils.FORWARD+"/login.jsp";
    }

    public String logOut(HttpServletRequest request,HttpServletResponse response){
        //1. 清除登录凭证
        request.getSession().removeAttribute(StrUtils.lOG_USER);

        //2.清除cookie
        Cookie cookie = new Cookie(StrUtils.AUTO_USER,"");
        cookie.setMaxAge(0);  //设置有效期为0,则表示立即清除
        response.addCookie(cookie);

        return StrUtils.REDIRECT+"/index.jsp";
    }




}
