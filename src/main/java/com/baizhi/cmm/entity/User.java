package com.baizhi.cmm.entity;

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

    private String username;

    private String password;

    private Integer logStatus;

    private String phone;

    private String img;

    private String dharma;

    private String realName;

    private Integer sex;

    private String province;

    private String city;

    private String signature;

    private Integer freStatus;

    private Guru guru;

    private String salt;

    private Date regDate;


}