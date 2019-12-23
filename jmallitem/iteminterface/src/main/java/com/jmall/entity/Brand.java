package com.jmall.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author jumping-張文平
 * @Date 2019/10/15 17:57
 * @Version 1.0
 */
@Data
@Table(name = "tb_brand")
public class Brand {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String image;
    private Character letter;
    //111111111111111111111111111111111111

}
