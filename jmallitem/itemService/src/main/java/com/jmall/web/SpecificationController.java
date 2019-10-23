package com.jmall.web;

import com.jmall.entity.SpecGroup;
import com.jmall.entity.SpecParam;
import com.jmall.service.impl.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/20 14:25
 * @Version 1.0
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @GetMapping("group/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupById(@PathVariable("cid") Long cid){
        List<SpecGroup> list = specificationService.queryGroupById(cid);
        return ResponseEntity.ok(list);
    }


    /**
     * 查询规格参数
     * 一个是根据组gid查询（默认不传值）
     * 一个是根据商品分类cid查询（默认不传值）
     * @param gid
     * @data 2019/10/23 19:02
     * [gid]
     * @return org.springframework.http.ResponseEntity<java.util.List<com.jmall.entity.SpecParam>>
     */
    @GetMapping("param")
    public ResponseEntity<List<SpecParam>> queryParamsList(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching
    ){
        List<SpecParam> list = specificationService.queryParamsById(gid,cid,searching);
        return ResponseEntity.ok(list);
    }
}
