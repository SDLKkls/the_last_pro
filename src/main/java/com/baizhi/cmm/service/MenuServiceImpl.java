package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Menu;
import com.baizhi.cmm.entity.MenuExample;
import com.baizhi.cmm.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> queryall() {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(0);
        return menuMapper.selectByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> queryallTree(Integer pid) {
        Menu menu = new Menu();
        menu.setParentId(pid);
        return menuMapper.select(menu);
    }
}
