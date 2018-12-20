package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> queryall();

    List<Menu> queryallTree(Integer pid);
}
