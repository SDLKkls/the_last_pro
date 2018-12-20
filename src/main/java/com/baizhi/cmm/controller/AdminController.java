package com.baizhi.cmm.controller;

import com.baizhi.cmm.entity.Admin;
import com.baizhi.cmm.exception.LoginException;
import com.baizhi.cmm.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public String login(Admin admin, HttpSession session, String code) {
        String scode = (String) session.getAttribute("code");
        if (!scode.equals(code)) throw new LoginException("验证码错误");
        Admin ad = adminService.login(admin);
        session.setAttribute("admin", ad);
        return "redirect:/main/main.jsp";
    }
}
