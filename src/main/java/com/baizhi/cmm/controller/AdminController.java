package com.baizhi.cmm.controller;

import com.baizhi.cmm.entity.Admin;
import com.baizhi.cmm.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @RequestMapping("loginAdmin")
    public String login(Admin admin, HttpSession session, String code) {
        String scode = (String) session.getAttribute("code");
        if (!scode.equals(code)) throw new LoginException("验证码错误");
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        subject.login(token);
        return "redirect:/main/main.jsp";
    }
}
