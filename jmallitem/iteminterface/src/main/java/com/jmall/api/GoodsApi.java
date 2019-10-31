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


    /**
     *分页查询Spu
     * @param page
	 * @param rows
	 * @param key
	 * @param saleable
     * @data 2019/10/31 14:20
     * [page, rows, key, saleable]
     * @return com.jmall.dto.PageResult<com.jmall.entity.Spu>
     */
    @GetMapping("/spu/page")
    PageResult<Spu> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable
    );

    /**
     *根据spuId查询SpuDetail
     * @param spuId
     * @data 2019/10/31 14:18
     * [spuId]
     * @return com.jmall.entity.SpuDetail
     */
    @GetMapping("/spu/detail/{id}")
    SpuDetail queryDetailById(@PathVariable("id") Long spuId);


    /**
     *根据spuId 查询所有sku
     * @param spuId
     * @data 2019/10/31 14:18
     * [spuId]
     * @return java.util.List<com.jmall.entity.Sku>
     */
    @GetMapping("sku/list")
    List<Sku> querySkuListBySpuId(@RequestParam("id") Long spuId);
}
