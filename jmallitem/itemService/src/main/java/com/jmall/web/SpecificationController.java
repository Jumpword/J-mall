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

    @GetMapping("param")
    public ResponseEntity<List<SpecParam>> queryParamsById(@RequestParam Long gid){
        List<SpecParam> list = specificationService.queryParamsById(gid);
        return ResponseEntity.ok(list);
    }
}
