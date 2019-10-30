package com.jmall.web;

import com.jmall.dto.PageResult;
import com.jmall.entity.Sku;
import com.jmall.entity.Spu;
import com.jmall.entity.SpuDetail;
import com.jmall.service.impl.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * 分页查询商品
     * @param page
	 * @param rows
	 * @param key
	 * @param saleable
     * @data 2019/10/24 20:55
     * [page, rows, key, saleable]
     * @return org.springframework.http.ResponseEntity<com.jmall.dto.PageResult<com.jmall.entity.Spu>>
     */
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",defaultValue = "true") Boolean saleable
    ){
        PageResult<Spu> spuPageResult = goodsService.querySpuByPage(page, rows, key, saleable);
        return ResponseEntity.ok(spuPageResult);
    }


    /**
     *新增商品
     * @param spu
     * @data 2019/10/24 20:54
     * [spu]
     * @return org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu){
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据spuId查询spuDetail
     *
     * @param spuId
     * @data 2019/10/24 16:55
     * [spuId]
     * @return org.springframework.http.ResponseEntity<com.jmall.entity.SpuDetail>
     */
    @GetMapping("/spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long spuId){
        SpuDetail spuDetail = goodsService.queryDetailByid(spuId);
        return ResponseEntity.ok(spuDetail);
    }



    /**
     * 根据spuId查询所有sku
     * @param spuId
     * @data 2019/10/24 17:33
     * [spuId]
     * @return org.springframework.http.ResponseEntity<java.util.List<com.jmall.entity.Sku>>
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuListBySId(@RequestParam("id") Long spuId){
        List<Sku> skuList = goodsService.querySkuListBySpuId(spuId);
        return ResponseEntity.ok(skuList);
    }
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu){
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
