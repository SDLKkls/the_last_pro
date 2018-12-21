package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;

public interface AlbumSerivce {
    AlbumDto queryAll(int page, int rows);

    Album queryOneById(Integer id);
}
