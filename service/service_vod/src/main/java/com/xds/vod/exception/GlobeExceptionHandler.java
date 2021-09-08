package com.xds.vod.exception;
import com.xds.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Bai Xu
 * @Date 2021/8/24 11:00
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobeExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了能够返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局处理异常");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了能够返回数据
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常");
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody//为了能够返回数据
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }


}
