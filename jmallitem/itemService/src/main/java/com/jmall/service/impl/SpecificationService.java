package com.jmall.service.impl;

import com.jmall.entity.SpecGroup;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.mapper.SpecGroupMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/20 14:24
 * @Version 1.0
 */
@Service
public class SpecificationService {
    @Autowired
    private SpecGroupMapper specGroupMapper;
    public List<SpecGroup> queryGroupById(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(list)){
            throw new JmallException(ExceptionEnum.SPECIFICATION_NOT_FOUND);
        }
        return list;
    }
}
