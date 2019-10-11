package com.jmall.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author jumping-張文平
 * @Date 2019/10/11 14:24
 * @Version 1.0
 */
@Table(name = "tb_Category")
@Data
public class Category  {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;


}
