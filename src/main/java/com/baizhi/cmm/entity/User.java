package com.baizhi.cmm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    private Integer id;

    @Excel(name = "账号")
    private String username;

    @Excel(name = "密码")
    private String password;

    private Integer logStatus;

    private String phone;

    private String img;

    private String dharma;

    private String realName;

    private Integer sex;

    @Excel(name = "省份")
    private String province;

    @Excel(name = "城市")
    private String city;

    private String signature;

    private Integer freStatus;

    private Guru guru;

    private String salt;

    @Excel(name = "注册时间")
    private Date regDate;


}