package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {  //封装Page实体
    private  int currentPage;  //当前页
    private  int pageSize;     //页大小
    private  long pageCount;   //总页数
    private List<Product> list;  //商品数据
}
