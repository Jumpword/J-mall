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
    CATEGORY_NOT_FOUND(404,"商品分类未找到！"),
    BRAND_NOT_FOUND(404,"品牌未找到！"),
    BRAND_SAVE_ERROR(500,"新增品牌失败!"),
    IMAGE_UPLOAD_ERROR(500,"上传图片失败!")
    ;
    private int code;
    private String msg;

}
