package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Province;
import com.baizhi.cmm.entity.User;
import com.baizhi.cmm.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Integer> getRegTime(int first, int second, int third) {
        Map<String, Integer> map = new HashMap<>();
        map.put("一周内", userMapper.queryCountByRegTime(new Date(), first));
        map.put("两周内", userMapper.queryCountByRegTime(new Date(), second));
        map.put("三周内", userMapper.queryCountByRegTime(new Date(), third));
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Province> getAddressCount() {
        return userMapper.queryProvinceCount();
    }

    @Override
    public void importUser(List<User> list) {
        userMapper.insertUserList(list);
    }
}
