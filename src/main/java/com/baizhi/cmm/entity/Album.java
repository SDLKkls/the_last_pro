package com.baizhi.cmm.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@ExcelTarget("album")
public class Album implements Serializable {
    @Id
    @Excel(name = "编号", needMerge = true)
    private Integer id;
    @Excel(name = "姓名", needMerge = true)
    private String name;
    @Excel(name = "包含章节数量", needMerge = true)
    private Integer chapterNum;
    @Excel(name = "播音人员", needMerge = true)
    private String broadcast;
    @Excel(name = "星级评分", needMerge = true)
    private Double rating;
    //    @Excel(name = "专辑图片", type = 2,imageType = 1,width = 40,height = 20)
    private String img;
    @Excel(name = "发布时间", format = "yyyy年MM月dd日", needMerge = true)
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;
    @Excel(name = "专辑描述", needMerge = true)
    private String brief;
    @ExcelEntity(name = "上师")
    private Guru guru;
    @ExcelCollection(name = "包含章节")
    private List<Chapter> children;

}