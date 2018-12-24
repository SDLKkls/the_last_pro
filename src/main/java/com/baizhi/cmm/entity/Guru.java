package com.baizhi.cmm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guru")
public class Guru implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Excel(name = "上师", needMerge = true)
    private String dharma;

    private String headPic;

    private Integer status;


}