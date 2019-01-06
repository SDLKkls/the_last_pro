package com.baizhi.cmm.mapper;

import com.baizhi.cmm.entity.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    List<Role> selectAllRole(Integer id);
}
