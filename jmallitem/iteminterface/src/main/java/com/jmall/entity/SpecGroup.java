package com.jmall.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jumping-張文平
 * @Date 2019/10/20 13:00
 * @Version 1.0
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long cid;
    private String name;

}
