package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;

import java.util.List;

public interface AlbumSerivce {
    AlbumDto queryAll(int page, int rows);

    Album queryOneById(Integer id);

    void insertOne(Album album);

    List<Album> getSimpleAll();

    List<Album> getAll();

    List<Album> queryAllNoPage();
}
