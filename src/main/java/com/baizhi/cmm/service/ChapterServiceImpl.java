package com.baizhi.cmm.service;


import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.entity.Chapter;
import com.baizhi.cmm.exception.ChapterException;
import com.baizhi.cmm.mapper.AlbumMapper;
import com.baizhi.cmm.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void insertOne(Chapter chapter) {
        if (chapter.getAlbumId() == null) throw new ChapterException("章节上传错误");
        Album album = new Album();
        album.setId(chapter.getAlbumId());
        Album selectOne = albumMapper.selectOne(album);
        if (selectOne == null) throw new ChapterException("章节上传错误，无所属专辑");
        selectOne.setChapterNum(selectOne.getChapterNum() + 1);
        albumMapper.updateByPrimaryKey(selectOne);
        chapter.setId(UUID.randomUUID().toString().replace("-", ""));
        chapter.setUploadDate(new Date());
        chapterMapper.insert(chapter);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Chapter queryOneById(String id) {
        Chapter chapter = new Chapter();
        chapter.setId(id);
        return chapterMapper.selectByPrimaryKey(chapter);
    }
}
