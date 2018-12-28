package com.baizhi.cmm.mapper;

import com.baizhi.cmm.entity.Province;
import com.baizhi.cmm.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface UserMapper extends Mapper<User> {
    Integer queryCountByRegTime(@Param("da") Date da, @Param("day") int first);

    List<Province> queryProvinceCount();

    void insertUserList(@Param("list") List<User> list);
}
