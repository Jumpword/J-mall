package com.jmall.web;

import com.jmall.dto.PageResult;
import com.jmall.entity.Brand;
import com.jmall.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/15 18:43
 * @Version 1.0
 */

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandServiceImpl brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){
       PageResult<Brand> pageResult = brandService.queryBrandByPage(
               page,rows,sortBy,desc,key);
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping("save")
    public ResponseEntity<Void> saveBrand(
            Brand brand, @RequestParam("cids") List<Long> cids
            ){
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable Long cid){
        List<Brand> brands = brandService.queryBrandByCid(cid);
        return ResponseEntity.ok(brands);
    }
}
