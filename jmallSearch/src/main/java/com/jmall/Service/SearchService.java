package com.jmall.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jmall.client.BrandClient;
import com.jmall.client.CategoryClient;
import com.jmall.client.GoodsClient;
import com.jmall.client.SpecificationClient;
import com.jmall.entity.*;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import com.jmall.pojo.Goods;
import com.jmall.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author jumping-張文平
 * @Date 2019/11/22 13:36
 * @Version 1.0
 *
 * 将查询的结果封装成拥有页面搜索展示信息的封装类Goods
 */
@Service
public class SearchService {



    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private SpecificationClient specificationClient;
    @Autowired
    private GoodsClient goodsClient;


    public Goods buildGoods(Spu spu){
        Long supId = spu.getId();
        Goods goods = new Goods();

      /*
      *
      * 往goods里面封装信息
      * goods 里面有哪些属性呢
      *
    @Id
    private Long id; //spuId
    @Field(type = FieldType.Text,index = false)
    private String all;//所有需要被搜索的信息，包含标题，分类，甚至品牌
    @Field(type = FieldType.Keyword,index = false)
    private String subTitle;//卖点 子标题
    private Long brandId;//品牌id
    private Long cid1;//1级分类id
    private Long cid2;//2级分类ld
    private Long cid3;//3级分类id
    private Date createTime;//spu创建时间
    private Set<Long> price;//价格
    @Field(type = FieldType.Keyword,index = false)
    private String skus;//sku信息的json结构
    private Map<String,Object> specs; //可搜索的规格参数，key是参数名，值是参数值
      *
      *
      *
      */


        //查询分类
        List<Category> categories = categoryClient.queryCategoryByIds(
                Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        if(CollectionUtils.isEmpty(categories)){
            throw new JmallException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        //查询品牌
        Brand brand = brandClient.queryBrandById(supId);
        if (brand==null){
            throw new JmallException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        //搜索字段  标题、分类、品牌  拼接字符串（可以考虑优化）
        String all = spu.getSubTitle()+
                StringUtils.join(names,"")+
                brand.getName();


        //查询sku
        List<Sku> skuList = goodsClient.querySkuListBySpuId(supId);
        if (CollectionUtils.isEmpty(skuList)){
            throw new JmallException(ExceptionEnum.GOODS_NOT_FOUND);
        }
     /*   Set<Long> prices = skuList.stream().
                map(Sku::getPrice).collect(Collectors.toSet());*/
        //对sku进行处理
        Set<Long> prices = new HashSet<>();
        List<Map<String,Object>> skus =new ArrayList<>();
        for (Sku sku : skuList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",sku.getId());
            map.put("title",sku.getTitle());
            map.put("price",sku.getPrice());
            map.put("images",StringUtils.substringBefore(sku.getImages(),","));
            prices.add(sku.getPrice());
        }



        //查询可搜索的规格参数
        List<SpecParam> params = specificationClient.
                queryParamList(null, spu.getCid3(), true);
        if(CollectionUtils.isEmpty(params)){
            throw new JmallException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        //查询商品详情
        SpuDetail spuDetail = goodsClient.queryDetailById(supId);

        //获取通用规格参数
        String specifications = spuDetail.getSpecifications();
        Map<Long, String> genericSpecification = JsonUtils.
                parseMap(specifications, Long.class, String.class);

        //获取特有规格参数
        String specTemplate = spuDetail.getSpecTemplate();
        Map<Long, List<String>> specTemplateMap = JsonUtils.nativeRead(specTemplate, new TypeReference<Map<Long, List<String>>>() {
        });

        //规格参数  key是规格参数的名字，value是规格参数的值
        HashMap<String, Object> specs = new HashMap<>();
        for (SpecParam param : params) {
            //规格参数名称
            String key = param.getName();

            //规格参数的值
            Object value = "";

            //判断是否为通用属性
            if(param.getGeneric()){
              value = genericSpecification.get(param.getId());
              //判断是否是数值类型
                if (param.getNumeric()) {

                }
            }else {
                value = specTemplateMap.get(param.getId());
            }
            //存入Map
            specs.put(key,value);

        }



        //构建goods对象
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setId(supId);
        goods.setSubTitle(spu.getSubTitle());


        goods.setAll(all);//  搜索字段，包括标题、分类、品牌

        goods.setPrice(prices);//  所有sku的价格 需要查找 根据spuId 查询所有sku的价格封装到集合当中

        goods.setSkus(JsonUtils.serialize(skuList));//  所有sku的json结构

        goods.setSpecs(specs); //TODO 所有可搜索的规格参数的map集合

        return goods;
    }
}
