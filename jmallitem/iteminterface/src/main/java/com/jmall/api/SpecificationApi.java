package com.jmall.api;

import com.jmall.entity.SpecParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 13:05
 * @Version 1.0
 */
public interface SpecificationApi {
    @RequestMapping("spec/params")
    List<SpecParam> queryParamList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching
    );
}
