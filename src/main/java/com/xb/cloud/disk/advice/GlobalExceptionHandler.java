package com.xb.cloud.disk.advice;

import com.xb.cloud.disk.exception.BusinessException;
import com.xb.cloud.disk.vo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jack
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handle(BusinessException e){
        LOGGER.error(e.getMessage(), e);
        return ApiResponse.fail(null, e.getMessage());
    }
}
