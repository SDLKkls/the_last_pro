package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Chapter;

public interface ChapterService {
    void insertOne(Chapter chapter);

    Chapter queryOneById(String id);
}
