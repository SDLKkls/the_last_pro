package com.baizhi.cmm.controller;

import com.baizhi.cmm.entity.Menu;
import com.baizhi.cmm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getmenu")
    public List<Menu> getMenu() {
        return menuService.queryall();
    }

    @RequestMapping("/gettree")
    public List<Menu> getTree(Integer pid) {
        return menuService.queryallTree(pid);
    }
}
