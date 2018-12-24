package com.baizhi.cmm.mapper;

import com.baizhi.cmm.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    List<Album> queryAll(@Param("page") int page, @Param("rows") int rows);

    Album selectOneById(Integer id);

    void insertOne(Album album);

    List<Album> getSimpleAll();

    List<Album> queryAllNoPage();
}
