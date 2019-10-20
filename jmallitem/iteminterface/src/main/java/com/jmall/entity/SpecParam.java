package com.jmall.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jumping-張文平
 * @Date 2019/10/20 18:22
 * @Version 1.0
 */
@Data
@Table(name = "tb_spec_param")
public class SpecParam {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    private Long grouId;
    private String name;
    @Column(name = "`numeric`")
    private Boolean numeric;//是否为数值类型
    private String unit;//数值类型单位，若是非数值类型那么为空
    private Boolean generic;//是否为sku通用属性
    private Boolean searching;//是否用于搜索过滤
    private String segments;//用于分段搜索
}
