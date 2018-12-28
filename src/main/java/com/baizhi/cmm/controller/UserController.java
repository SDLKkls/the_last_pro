package com.baizhi.cmm.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.cmm.entity.Province;
import com.baizhi.cmm.entity.User;
import com.baizhi.cmm.service.UserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getRegTime")
    public Map<String, Integer> getRegTime() {
        return userService.getRegTime(7, 14, 21);
    }

    @RequestMapping("/getAddressCount")
    public List<Province> getAddressCount() {
        return userService.getAddressCount();
    }

    @RequestMapping("/regist")
    public void regist() {

        Map<String, Integer> map = userService.getRegTime(7, 14, 21);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-2b66fbf505a54de1a1ca0b060dc1be20");
        goEasy.publish("regTimeMap", JSON.toJSONString(map));
    }

    @RequestMapping("/import")
    public void importUser(MultipartFile impFile, HttpSession session) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<User> list = ExcelImportUtil.importExcel(impFile.getInputStream(), User.class, params);
            log.info(list.toString());
            userService.importUser(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
