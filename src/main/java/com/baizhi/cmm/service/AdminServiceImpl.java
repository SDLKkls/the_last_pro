package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Admin;
import com.baizhi.cmm.exception.LoginException;
import com.baizhi.cmm.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin login(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setUsername(admin.getUsername());
        List<Admin> list = adminMapper.select(admin1);
        if (list.size() == 0) throw new LoginException("账号错误");
        boolean b = false;
        for (Admin a : list) {
            if (a.getPassword().equals(admin.getPassword())) {
                b = true;
            }
        }
        if (!b) throw new LoginException("密码错误");
        return list.get(0);
    }
}
