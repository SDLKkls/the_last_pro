package com.baizhi.cmm.controller;

import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.service.AlbumSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumSerivce albumSerivce;

    @RequestMapping("/getAll")
    public AlbumDto getAll(int page, int rows) {
        return albumSerivce.queryAll(page, rows);
    }

    @RequestMapping("/getOneById")
    public Album getOneById(Integer id) {
        return albumSerivce.queryOneById(id);
    }
}
