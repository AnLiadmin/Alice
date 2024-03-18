package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private int p_id;
  private int t_id;
  private String p_name;
  private Date p_time;
  private String p_image;
  private BigDecimal p_price;  //精确的double类型
  private int p_state;  //热销指数 *  ** ***
  private String p_info;
}
