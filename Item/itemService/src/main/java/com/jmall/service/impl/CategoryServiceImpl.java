package com.jmall.service.impl;
import com.jmall.entity.Category;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.mapper.CaregoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 14:39
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl {
    @Autowired
    private CaregoryMapper caregoryMapper;

    public List<Category> queryCategoryByPid(Long pid) {
        Category t = new Category();
        t.setParentId(pid);
        //此方法为通用mapper根据对象的非空属性作为查询条件
        List<Category> categoryList = caregoryMapper.select(t);
        if(CollectionUtils.isEmpty(categoryList)){
            throw new JmallException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }
}
