package com.baizhi.cmm.controller;

import com.baizhi.cmm.entity.Province;
import com.baizhi.cmm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
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
}
