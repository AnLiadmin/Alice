package com.qf.dao;

import com.qf.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> selectByTid(String tid);

    long getTotalCount(String tid);

    List<Product> selectByPage(String tid, int startIndex, int pageSize);

    Product selectByPid(String pid);
}
