package com.jmall.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author jumping-張文平
 * @Date 2019/10/24 11:55
 * @Version 1.0
 */
@Table(name = "tb_sku")
@Data
public class Sku {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String ownSpec;//特有规格参数
    private String indexes;//商品特有规格下标
    private Boolean enable;//是否有用，用于逻辑删除
    private Date createTime;
    private Date lastUpdateTime;

    @Transient
    private Integer stock;//库存


}
