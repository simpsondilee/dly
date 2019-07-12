package com.springboot.core.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface Mapper<T> extends BaseMapper, ConditionMapper<T>, IdsMapper<T> ,InsertListMapper<T> {

}
