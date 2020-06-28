package com.zrf.blog.advice;

import com.zrf.blog.enums.ResultEnum;
import com.zrf.blog.exception.BlogException;
import com.zrf.blog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author 张润发
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    /**
     * 统一处理 BlogException
     *
     * @param e
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException e) {
        log.error("自定义统一异常处理:", e);
        return new Result<>(e.getErrorCode(), e.getMessage());
    }

    /**
     * 实体类参数校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder(16);
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.substring(0, sb.length() - 1);
        return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), msg);
    }

    /**
     * 对于路径参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder sb = new StringBuilder(16);
        for (ConstraintViolation<?> item : violations) {
            sb.append(item.getMessage()).append(",");
        }
        String msg = sb.substring(0, sb.length() - 1);
        return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), msg);
    }


    /**
     * 空异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result<Object> nullPointerExceptionHandler(NullPointerException e) {
        log.error("空异常处理:", e);
        return new Result<>(-1, "发生了空异常");
    }

    /**
     * 公共异常处理
     *
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> commonExceptionHandler(Exception e) {
        log.error("未知异常处理:", e);
        return new Result<>(-1, e.getMessage());
    }

}
