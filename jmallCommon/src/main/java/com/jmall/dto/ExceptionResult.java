package com.jmall.dto;

import com.jmall.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author jumping-張文平
 * @Date 2019/10/2 9:29
 * @Version 1.0
 */
@Data
public class ExceptionResult {
    private int status;
    private String msg;
    private Long timetamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status =  em.getCode();
        this.msg = em.getMsg();
        this.timetamp = System.currentTimeMillis();

    }
}
