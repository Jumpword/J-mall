package com.jmall.advice;

import com.jmall.dto.ExceptionResult;
import com.jmall.enums.ExceptionEnum;
import com.jmall.exception.JmallException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * 对controller层的异常处理
 *
 * @Author jumping-張文平
 * @Date 2019/10/2 8:30
 * @Version 1.0
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(JmallException.class)
    public ResponseEntity<ExceptionResult> handleException(JmallException e){
        return  ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
