package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumSerivce {
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public AlbumDto queryAll(int page, int rows) {
        return new AlbumDto(albumMapper.selectCount(new Album()), albumMapper.queryAll(page, rows));
    }

    @Override
    public Album queryOneById(Integer id) {
        Album album = new Album();
        album.setId(id);
        return albumMapper.selectOne(album);
    }
}
