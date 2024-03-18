package com.qf.controller;

import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.AddrService;
import com.qf.service.impl.AddrServiceImpl;
import com.qf.utils.StrUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Struct;
import java.util.List;
import java.util.Map;

@WebServlet("/addr")
public class AddrController extends BaseServlet {
    private AddrService addrService = new AddrServiceImpl();
    public String addAddress(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        Address addr = new Address();
        BeanUtils.populate(addr,map);
        addr.setA_state(0);  //状态为0表示非默认地址
        int res = addrService.addAddress(addr);  //添加地址
        System.out.println("添加: "+res);
        return StrUtils.REDIRECT+"/addr?action=showAddr";
    }

    public String showAddr(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //根据uid获取地址集合
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        List<Address> list = addrService.selectByUid(u.getU_id());
        request.setAttribute("addList",list);
        return StrUtils.FORWARD+"/self_info.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        String aid = request.getParameter("aid");
        int res = addrService.deleteByAid(aid);  //根据aid删除
        System.out.println("删除:"+res);
        return StrUtils.REDIRECT+"/addr?action=showAddr";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        Address addr = new Address();
        BeanUtils.populate(addr,map);
        int res = addrService.updateAddr(addr);  //根据地址修改
        System.out.println("修改:"+res);
        return StrUtils.REDIRECT+"/addr?action=showAddr";
    }

    public String updateDefault(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        String aid = request.getParameter("aid");
        User u = (User) request.getSession().getAttribute(StrUtils.lOG_USER);
        int res = addrService.updateDefault(aid,u.getU_id()); //设置默认
        return StrUtils.REDIRECT+"/addr?action=showAddr";
    }
}
