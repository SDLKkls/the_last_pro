package com.baizhi.cmm.controller;

import com.baizhi.cmm.entity.Chapter;
import com.baizhi.cmm.exception.UploadException;
import com.baizhi.cmm.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
@Slf4j
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/insertOne")
    public boolean insertOne(HttpSession session, MultipartFile upMp, Chapter chapter) {
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/center/mp3");

        String filename = UUID.randomUUID().toString().replace("-", "") + "_" + upMp.getOriginalFilename();
        File file = new File(realPath + "/" + filename);
        try {
            upMp.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UploadException("文件上传错误");
        }
        chapter.setAudioAddr("/" + filename);

        /*获取文件大小，并转换成mb*/
        chapter.setSize(upMp.getSize() * 1.0 / 1024 / 1024);

        /*获取文件时长*/
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            chapter.setDuration(audioHeader.getTrackLength() + 0.0 / 60);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chapterService.insertOne(chapter);
        return true;
    }

    @RequestMapping("/download")
    public void downloadOne(HttpSession session, HttpServletResponse response, String id) {
        String realPath = session.getServletContext().getRealPath("/center/mp3");
        Chapter chap = chapterService.queryOneById(id);
        ServletOutputStream stream = null;
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(realPath + chap.getAudioAddr()));
            String[] split = chap.getAudioAddr().split("_");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(split[1], "UTF-8"));
            response.setContentType("application/x-msdownload");
            response.setContentLength(bytes.length);
            stream = response.getOutputStream();
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) stream.flush();
                if (stream != null) stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("success");
    }
}
