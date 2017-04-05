package win.liumian.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import win.liumian.exception.HttpException;

/**
 * Created by liumian on 2017/4/4.
 */
@ControllerAdvice
public class ExceptionAop {


    @ExceptionHandler(value = HttpException.class)
    public String handlerHttpException(){
        return "http request has wrong!";
    }

}
