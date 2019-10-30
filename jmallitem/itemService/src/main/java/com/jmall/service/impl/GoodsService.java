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

import javax.management.JMException;
import java.util.*;
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
    @Autowired
    private StockMapper stockMapper;
    /**
     * 分页查询
     * @param page,rows,key,saleable
     * @data 2019/10/24 20:58
     *
     * @return
     */
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
    /**
     * 将分类名称和品牌名称关联到一张表
     * @param spus
     * @data 2019/10/24 20:59
     *
     * @return
     */
    private void loadCategoryAndBrand(List<Spu> spus) {
        Category category = new Category();
        for (Spu spu : spus) {
            //1）分类名称
            //   2.得到名称集合
            List<String> names = categoryService.queryById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).
                    stream().map(Category::getName).collect(Collectors.toList());
            //   3.将集合拼接成字符串
            spu.setCName(StringUtils.join(names,"/"));
            //2) 品牌名称
            spu.setBName(brandService.queryBrandById(spu.getBrandId()).getName());
        }
    }


    /**
     *新增商品
     *
     * @param spu
     * @data 2019/10/24 21:01
     * [spu]
     * @return void
     */
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
        spuDetailMapper.insert(spuDetail);

        //新增Sku和Stock
        saveSkuAndStock(spu);
    }

    private void saveSkuAndStock(Spu spu){
        int count;
        //定义一个库存集合
        List<Stock> stockList = new ArrayList<>();
        //3.新增sku
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            count = skuMapper.insert(sku);
            if (count!=1){
                throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
            }

            //4.新增库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }

        //批量新增库存
        count = stockMapper.insertList(stockList);
        if (count!=stockList.size()){
            throw new JmallException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

    }
    /**
     *根据商品id查询SpuDetail
     * @param spuId
     * @data 2019/10/24 21:02
     *
     * @return SpuDetail
     */
    public SpuDetail queryDetailByid(Long spuId) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        if (spuDetail==null){
            throw new JmallException(ExceptionEnum.GOODS_DETAIL_NOT_FOUND);
        }
        return spuDetail;
    }

    /**
     *根据spuId查询所有的sku
     * @param spuId
     * @data 2019/10/24 21:03
     *
     * @return
     */
    public List<Sku> querySkuListBySpuId(Long spuId) {
        //查询sku
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)){
            throw new JmallException(ExceptionEnum.GOODS_SKU_NOT_FOUND);
        }
        //查询库存
      /*  for (Sku s : skuList) {
            Stock stock = stockMapper.selectByPrimaryKey(s.getId());
            if (stock==null){
                throw new JmallException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
            }
            s.setStock(stock.getStock());
        }*/
        List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(stockList)){
            throw new JmallException(ExceptionEnum.GOODS_STOCK_NOT_FOUND);
        }
        //我们把stock变成一个map，其中key是：sku的id，值是库存值
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));
        return skuList;
    }

    /**
     *修改商品信息
     * @param spu
     * @data 2019/10/24 21:03
     *
     * @return
     */
    @Transactional
    public void updateGoods(Spu spu) {
        if (spu.getId()==null){
            throw new JmallException(ExceptionEnum.GOODS_ID_CANNOT_BE_NULL);
        }
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        //查询sku
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)){
            //删除sku(每个spuId 只对应一种类型的商品 如 IPhone 8)
            skuMapper.delete(sku);
            //删除stock
            List<Long> skuIds = skuList.stream().map(Sku::getId)
                    .collect(Collectors.toList());
            stockMapper.deleteByIdList(skuIds);
        }

        //修改spu
        spu.setValid(null);//是否有效
        spu.setSaleable(null);//是否上架
        spu.setLastUpdateTime(new Date());
        spu.setCreateTime(null);
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if(count!=1){
            throw new JmallException(ExceptionEnum.GOODS_UPDATE_ERROR);
        }
        //修改detail
         count = spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (count!=1){
            throw new JmallException(ExceptionEnum.GOODS_DETAIL_UPDATE_ERROR);
        }
        //新增和stock
        saveSkuAndStock(spu);
    }
}
