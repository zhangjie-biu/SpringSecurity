package com.zhangjie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjie.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {


    List<String> selectPermsByUserId(Long userid);
}
