package com.jmall.api;

import com.jmall.dto.PageResult;
import com.jmall.entity.Sku;
import com.jmall.entity.Spu;
import com.jmall.entity.SpuDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jumping-張文平
 * @Date 2019/10/31 13:52
 * @Version 1.0
 */
public interface GoodsApi {

    @GetMapping("/spu/page")
    PageResult<Spu> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable
    );

    @PostMapping("goods")
    Void saveGoods(@RequestBody Spu spu);

    @GetMapping("/spu/detail/{id}")
    SpuDetail queryDetailById(@PathVariable("id") Long spuId);

    @GetMapping("sku/list")
    List<Sku> querySkuListBySId(@RequestParam("id") Long spuId);

    @PutMapping("goods")
    Void updateGoods(@RequestBody Spu spu);
}
