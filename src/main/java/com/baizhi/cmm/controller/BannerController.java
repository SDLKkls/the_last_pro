package com.baizhi.cmm.controller;

import com.baizhi.cmm.dto.BannerDto;
import com.baizhi.cmm.entity.Banner;
import com.baizhi.cmm.exception.UploadException;
import com.baizhi.cmm.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
@Slf4j
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getAll")
    public BannerDto getAll(int page, int rows) {
        return bannerService.queryAll(page, rows);
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner) {
        bannerService.updateBanner(banner);
    }

    @RequestMapping("/deleteBanner")
    public String deleteBanner(Banner banner, HttpSession session) {
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/center/img");
        bannerService.deleteBanner(banner, realPath);
        return "";
    }

    @RequestMapping("/insertBanner")
    public boolean insertBanner(Banner banner, MultipartFile uploadImg, HttpSession session) {
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/center/img");
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + uploadImg.getOriginalFilename();
        File file = new File(realPath + "/" + fileName);
        try {
            uploadImg.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException("文件上传错误");
        }
        banner.setImgPath("/" + fileName);
        banner.setPubDate(new Date());
        bannerService.insertBanner(banner);
        return true;
    }
}
