package com.qf.service;

import com.qf.entity.Page;
import com.qf.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> selectByTid(String tid);

    Page getPage(String tid, String current);

    Product selectByPid(String pid);
}
