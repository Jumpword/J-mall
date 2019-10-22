package com.jmall.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jumping-張文平
 * @Date 2019/10/21 18:45
 * @Version 1.0
 *
 * 垂直拆分表 把信息多的单独从spu表中分离开来
 */

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    private Long spuId;//这个是spu的id所以不用自增
    private String description;//商品的描述信息
    private String specifications;//全部规格参数数据
    private String specTemplate;//特有规格参数及可选值模板 ，json格式,即将sku的特有属性抽取出来 比如颜色有多种，内存有多种
    private String packingList;//包装清单
    private String afterService;//售后服务
}
