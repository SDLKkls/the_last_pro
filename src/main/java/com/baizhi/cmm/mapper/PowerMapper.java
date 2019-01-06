package com.baizhi.cmm.mapper;

import com.baizhi.cmm.entity.Power;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PowerMapper extends Mapper<Power> {

    List<Power> selectAllPower(Integer id);
}
