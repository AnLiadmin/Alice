package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  private int i_id;  //详情id
  private String o_id;  //订单id
  private int p_id;     //商品id
  private BigDecimal i_count;  //小计
  private int i_num;   //数量

  private Product goods;  //详情中包含商品对象
}
