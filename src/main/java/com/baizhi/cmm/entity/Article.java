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
@Table(name = "article")
public class Article implements Serializable {
    @Id
    private Integer id;

    private String name;

    private Date pubDate;

    private String contents;

    private Guru guru;

}