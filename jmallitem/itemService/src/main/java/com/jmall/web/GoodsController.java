package com.jmall.web;

import com.jmall.dto.PageResult;
import com.jmall.entity.Spu;
import com.jmall.service.impl.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author jumping-張文平
 * @Date 2019/10/21 19:27
 * @Version 1.0
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> qureySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable
    ){
        PageResult<Spu> spuPageResult = goodsService.querySpuByPage(page, rows, key, saleable);
        return ResponseEntity.ok(spuPageResult);
    }
}
