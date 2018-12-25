package com.baizhi.cmm;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmm.dto.AlbumDto;
import com.baizhi.cmm.entity.Album;
import com.baizhi.cmm.service.AlbumSerivce;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasypoiTest {
    @Autowired
    AlbumSerivce albumSerivce;

    @Test
    public void test1() {

        AlbumDto dto = albumSerivce.queryAll(1, 10);
        List<Album> all = dto.getRows();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑表一览", "专辑"), Album.class, all);
        try {
            workbook.write(new FileOutputStream(new File("D:/a.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

    }
}
