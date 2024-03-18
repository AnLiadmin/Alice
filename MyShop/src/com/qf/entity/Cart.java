package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
  private int c_id;  //购物车ID
  private int u_id;  //用户ID
  private int p_id;  //商品ID
  private BigDecimal c_count;  //小计
  private int c_num;  //数量

  //在购物车中,包含商品对象
  private  Product goods;
}
