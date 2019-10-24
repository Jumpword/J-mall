package com.jmall.mapper;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author jumping-張文平
 * @Date 2019/10/24 15:54
 * @Version 1.0
 */
public interface BaseMapper<T> extends Mapper<T>, IdListMapper<T,Long>, InsertListMapper {
}
