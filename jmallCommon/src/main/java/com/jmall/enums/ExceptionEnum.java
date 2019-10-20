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
    CATEGORY_NOT_FOUND(404,"商品分类未找到"),
    BRAND_NOT_FOUND(404,"品牌未找到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    FILE_UPLOAD_ERROR(500,"上传文件失败"),
    INVALID_FILE_TYPE(500,"无效的文件类型"),
    SPECIFICATION_NOT_FOUND(404,"商品规格组未找到")
    ;
    private int code;
    private String msg;

}
