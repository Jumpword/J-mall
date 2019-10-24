package com.jmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jmall.dto.PageResult;
import com.jmall.entity.*;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.mapper.SkuMapper;
import com.jmall.mapper.SpuDetailMapper;
import com.jmall.mapper.SpuMapper;
import com.jmall.mapper.StockMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author jumping-張文平
 * @Date 2019/10/21 19:24
 * @Version 1.0
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private BrandServiceImpl brandService;
    @Autowired
    private SkuMapper skuMapper;

    private StockMapper stockMapper;
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {
        //分页 提供当前页面和页面条数
        PageHelper.startPage(page,rows);
        //过滤 两个用and
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%");
        }
        if (saleable!=null){
            criteria.andEqualTo("saleable",saleable);
        }
        //默认排序
        example.setOrderByClause("last_update_time DESC");

        //查询
        List<Spu> spus = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spus)){
            throw new JmallException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        //解析分页和品牌的名称
        loadCategoryAndBrand(spus);
        //解析分页对象
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);

        return new PageResult<>(spuPageInfo.getTotal(),spus);
    }

    private void loadCategoryAndBrand(List<Spu> spus) {
        Category category = new Category();
        for (Spu spu : spus) {
            //1）1.分类名称
            //   2.得到名称集合
            List<String> names = categoryService.queryById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).
                    stream().map(Category::getName).collect(Collectors.toList());
            //   3.将集合拼接成字符串
            spu.setCName(StringUtils.join(names,"/"));
            //2) 1.品牌名称
            spu.setBName(brandService.queryBrandById(spu.getBrandId()).getName());
        }
    }

    @Transactional
    public void saveGoods(Spu spu) {
        //1.新增spu
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setId(null);
        spu.setSaleable(true);
        spu.setValid(false);//是否有效
        int count = spuMapper.insert(spu);
        if (count!=1){
            throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

        //2.新增spuDetail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        int count1 = spuDetailMapper.insert(spuDetail);
        if(count1!=1){
            throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
        }
        //定义一个库存集合
        List<Stock> stockList = new ArrayList<>();
        //3.新增sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            int count2 = skuMapper.insert(sku);
            if (count2!=1){
                throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            //4.新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }

        //批量新增库存
        int count3 = stockMapper.insertList(stockList);
        if (count3!=stockList.size()){
            throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
        }


    }

    public SpuDetail queryDetailByid(Long id) {
        return null;
    }
}
