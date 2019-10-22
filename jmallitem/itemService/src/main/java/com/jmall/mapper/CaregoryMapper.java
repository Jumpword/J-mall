package com.jmall.mapper;

import com.jmall.entity.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 14:35
 * @Version 1.0
 */
public interface CaregoryMapper extends Mapper<Category>, IdListMapper<Category,Long> {
}
