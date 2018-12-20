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
@Table(name = "album")
public class Album implements Serializable {
    @Id
    private Integer id;

    private String name;

    private Integer chapterNum;

    private String broadcast;

    private Double rating;

    private String img;

    private Date pubDate;

    private String brief;

    private Guru guru;

}