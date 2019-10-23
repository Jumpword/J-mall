package com.jmall.service.impl;

import com.jmall.entity.SpecGroup;
import com.jmall.entity.SpecParam;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.mapper.SpecGroupMapper;
import com.jmall.mapper.SpecParamMapper;
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
    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryGroupById(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = specGroupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(list)){
            throw new JmallException(ExceptionEnum.SPECIFICATION_NOT_FOUND);
        }
        return list;
    }

    public List<SpecParam> queryParamsById(Long gid,Long cid,Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        List<SpecParam> list = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(list)){
            throw new JmallException(ExceptionEnum.SPEC_PARAME_NOT_FOUND);
        }
        return list;
    }
}
