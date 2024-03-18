package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {  //用表
  private int u_id;    //属性与表字段名要对应,否则容易ORM映射失败
  private String u_name;  //用户名
  private String u_password;  //密码
  private String u_email;     //邮箱
  private String u_sex;       //性别
  private int u_status;       //状态
  private String u_code;      //激活码
  private int u_role;         //角色
}
