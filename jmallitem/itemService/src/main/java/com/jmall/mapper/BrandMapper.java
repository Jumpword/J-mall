package com.jmall.mapper;

import com.jmall.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
/**
 * @Author jumping-張文平
 * @Date 2019/10/15 18:36
 * @Version 1.0
 */
public interface BrandMapper extends Mapper<Brand> {
    @Insert("INSERT INTO tb_category_brand (brand_id,category_id) VALUES (#{bid},#{cid}) ")
   int insertBrandCategory(@Param("brand_id") Long bid,@Param("category_id") Long cid);
}
