package com.baizhi.cmm.service;

import com.baizhi.cmm.entity.Province;
import com.baizhi.cmm.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Integer> getRegTime(int first, int second, int third);

    List<Province> getAddressCount();

    void importUser(List<User> list);
}
