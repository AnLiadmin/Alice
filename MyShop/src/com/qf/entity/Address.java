package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private int a_id;  //地址id
  private int u_id;  //用户id
  private String a_name;  //收货人
  private String a_phone;  //电话
  private String a_detail;  //收货地址
  private int a_state;     //收货状态-默认-1,非默认-0
}
