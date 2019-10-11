package com.jmall.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author jumping-張文平
 * @Date 2019/10/2 9:12
 * @Version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(400,"价格不能为空！"),
    CATEGORY_NOT_FOUND(404,"商品分类未找到！")
    ;
    private int code;
    private String msg;

}
