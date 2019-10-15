package com.jmall.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @Author jumping-張文平
 * @Date 2019/10/15 19:01
 * @Version 1.0
 *
 * 视图对象
 * 将分页结果单独封装为一个类
 * 因它将是一个通用的分页结果类，所以我们将它作为一个泛型，以便后面我们作其它查询也可以用
 */
@Data
@NoArgsConstructor
public class PageResult<T> {
    private Long total;//返回总条数
    private List<T> items;//当前页数据
    private Integer page;//当前页码

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, List<T> items, Integer page) {
        this.total = total;
        this.items = items;
        this.page = page;
    }
}
