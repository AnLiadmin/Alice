package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
  private String o_id;   //订单id
  private int u_id;      //用户id
  private int a_id;      //地址id
  private BigDecimal o_count;  //总金额
  private Date o_time;   //订单时间
  private int o_state;   //订单状态: 1.未支付  2.已支付,未发货...

  private Address addr;  //订单中包含地址对象
  private List<Item> list;  //订单中包含详情集合
}
