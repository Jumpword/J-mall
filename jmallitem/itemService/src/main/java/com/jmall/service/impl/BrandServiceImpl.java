package com.jmall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jmall.dto.PageResult;
import com.jmall.entity.Brand;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.mapper.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.jar.JarException;

/**
 * @Author jumping-張文平
 * @Date 2019/10/15 18:40
 * @Version 1.0
 */
@Service
public class BrandServiceImpl {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 分页
        Page<Object> objects = PageHelper.startPage(page, rows);

        //  过滤
        Example example = new Example(Brand.class);
        if(StringUtils.isNoneBlank(key)){
            //过滤条件
            example.createCriteria().orLike("name","%"+key+"%")
                    .orEqualTo("letter",key.toUpperCase());

        }
        // 排序
        if(StringUtils.isNotBlank(sortBy)){

            // ORDER BY id DESC 根据哪个字段排序是否要降序
            String clause = sortBy+(desc ? "DESC" : "ASC");
            example.setOrderByClause(clause);
        }

        // 查询
        List<Brand> brands = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(brands)){
            throw new JmallException(ExceptionEnum.BRAND_NOT_FOUND);
        }

        //解析分页结果
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        return new PageResult<>(brandPageInfo.getTotal(),brands);
    }
}
