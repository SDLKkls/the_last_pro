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
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    private String id;

    private String name;

    private Double size;

    private Double duration;

    private String audioAddr;

    private Integer status;

    private Date uploadDate;

    private Integer albumId;


}