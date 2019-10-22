package com.jmall.vo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author jumping-張文平
 * @Date 2019/10/21 23:08
 * @Version 1.0
 */
@Data
@Table(name = "tb_spu")
public class SpuVo {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1;//一级目录
    private Long cid2;//二级目录
    private Long cid3;//三级目录
    private String name;//标题
    private String subTitle;//子标题
    private Boolean saleable;//是否上架
    private Boolean valid;//是否有效，逻辑删除用
    private Date createTime;
    private Date lastUpdateTime;

    @Transient
    private String bName;
    @Transient
    private String cName;

}
