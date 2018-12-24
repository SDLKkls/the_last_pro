package com.baizhi.cmm.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.exception.UploadException;
import com.baizhi.cmm.service.AlbumSerivce;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/insertOne")
    public boolean insertOne(Album album, MultipartFile imgFile, HttpSession session) {
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/center/img");
        String filename = UUID.randomUUID().toString().replace("-", "") + imgFile.getOriginalFilename();

        File file = new File(realPath + "/" + filename);
        try {
            imgFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException("文件上传错误");
        }
        album.setImg("/" + filename);
        albumSerivce.insertOne(album);
        return true;
    }

    @RequestMapping("/getSimpleAll")
    public List<Album> getSimpleAll() {
        return albumSerivce.getSimpleAll();
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<Album> albumList = albumSerivce.queryAllNoPage();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑信息一览表", "专辑"), Album.class, albumList);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("专辑信息一览表.xls", "UTF-8"));
            response.setContentType("application/x-msdownload");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importExcel")
    public void importExcel() {

    }
}
