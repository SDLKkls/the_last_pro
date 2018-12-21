package com.baizhi.cmm.mapper;

import com.baizhi.cmm.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    List<Album> queryAll(int page, int rows);
}
