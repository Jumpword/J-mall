package com.jmall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author jumping-張文平
 * @Date 2019/10/21 18:34
 * @Version 1.0
 */
@Table(name = "tb_spu")
@Data
public class Spu {
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
    @JsonIgnore
    private Boolean valid;//是否有效，逻辑删除用
    private Date createTime;
    @JsonIgnore
    private Date lastUpdateTime;
    //以下是数据没有的字段
    @Transient
    private String bName;
    @Transient
    private String cName;

}
