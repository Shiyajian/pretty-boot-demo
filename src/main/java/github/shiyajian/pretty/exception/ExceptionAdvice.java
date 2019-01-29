package github.shiyajian.pretty.exception;

import github.shiyajian.pretty.commons.ResponseVO;
import github.shiyajian.pretty.commons.ServiceThrowable;
import github.shiyajian.pretty.utils.I18nMessageUtil;
import github.shiyajian.pretty.utils.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author shiyajian
 * create: 2019-01-27
 */
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 所有业务异常统一处理入口 （默认 HttpStatus.OK = 200）
     * @param exception 业务异常
     * @return 全局统一返回体
     */
    @ExceptionHandler(ServiceThrowable.class)
    public ResponseVO<String> handleServiceException(ServiceThrowable exception) {
        return ResponseUtil.failed(exception.getResponseEnum(),
                I18nMessageUtil.getMessage(exception.getKey(), exception.getArgs()));
    }
}
