package com.jmall.exception;

import com.jmall.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author jumping-張文平
 * @Date 2019/10/2 9:09
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JmallException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}
