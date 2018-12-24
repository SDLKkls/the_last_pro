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
@Table(name = "chapter")
public class Chapter implements Serializable {
    @Id
    private String id;
    @Excel(name = "章节名")
    private String name;
    @Excel(name = "章节大小")
    private Double size;
    @Excel(name = "章节时长")
    private Double duration;
    @Excel(name = "章节地址")
    private String audioAddr;
    @Excel(name = "章节状态", replace = {"可用_0", "不可用_1"})
    private Integer status;
    @Excel(name = "发布时间", format = "yyyy年MM月dd日")
    private Date uploadDate;

    private Integer albumId;


}