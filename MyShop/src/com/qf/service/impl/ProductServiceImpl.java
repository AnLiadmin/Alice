package com.qf.service.impl;

import com.qf.dao.ProductDao;
import com.qf.dao.UserDao;
import com.qf.dao.impl.ProductDaoImpl;
import com.qf.entity.Page;
import com.qf.entity.Product;
import com.qf.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> selectByTid(String tid) {
        return productDao.selectByTid(tid);
    }

    @Override
    public Page getPage(String tid, String current) { //分页数据填充
        int currentPage = 1;  //默认当前页为1
        int pageSize = 4;     //页大小为4
        if(current!=null){
            currentPage = Integer.parseInt(current); //String转int
        }
        Page page = new Page();
        page.setCurrentPage(currentPage);  //设置当前页
        page.setPageSize(pageSize);        //设置页大小
        //获取总条数
        long totalCount = productDao.getTotalCount(tid);  //根据tid返回总条数
        long pageCount = (long)(Math.ceil((double)totalCount/pageSize)); //向上取
        page.setPageCount(pageCount);  //页总数  12条/4==3  13条/4+1==4
        int startIndex = (currentPage-1)*pageSize;  //求起始行
        List<Product> list = productDao.selectByPage(tid,startIndex,pageSize); //分页查
        page.setList(list);
        return page;
    }

    @Override
    public Product selectByPid(String pid) {
        return productDao.selectByPid(pid);
    }
}
