package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.entity.Guru;
import com.baizhi.cmm.mapper.AlbumMapper;
import com.baizhi.cmm.mapper.GuruMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumSerivce {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private GuruMapper guruMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public AlbumDto queryAll(int page, int rows) {
        return new AlbumDto(albumMapper.selectCount(new Album()), albumMapper.queryAll(page, rows));
    }

    @Override
    public Album queryOneById(Integer id) {
        return albumMapper.selectOneById(id);
    }

    @Override
    public void insertOne(Album album) {
        album.setChapterNum(0);
        album.setPubDate(new Date());
        album.setRating(0.0);
        Guru one = guruMapper.selectOne(album.getGuru());
        if (one == null) {
            Guru sguru = album.getGuru();
            sguru.setStatus(0);
            guruMapper.insert(sguru);
            album.setGuru(sguru);
            albumMapper.insertOne(album);
        } else {
            album.setGuru(one);
            albumMapper.insertOne(album);
        }
    }

    @Override
    public List<Album> getSimpleAll() {
        return albumMapper.getSimpleAll();
    }

    @Override
    public List<Album> getAll() {
        return albumMapper.selectAll();
    }

    @Override
    public List<Album> queryAllNoPage() {
        return albumMapper.queryAllNoPage();
    }

}
