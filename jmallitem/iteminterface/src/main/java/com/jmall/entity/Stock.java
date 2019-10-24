package com.jmall.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jumping-張文平
 * @Date 2019/10/24 12:07
 * @Version 1.0
 */
@Data
@Table(name = "tb_stock")
public class Stock {
    @Id
    private Long skuId;
    private Integer stock;//正常库存
    private Integer secKillStock;//可秒杀库存量
    private Integer secKillTotal;//秒杀总数量
}
