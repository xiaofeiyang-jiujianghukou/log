package com.base.kit.log.common.processor;

import com.base.kit.log.common.Result;
import com.base.kit.log.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    Result<?> handleBizException(BizException e) {
        return Result.fail(e.getResultEnum().getCode(), e.getMessage());
    }

}
