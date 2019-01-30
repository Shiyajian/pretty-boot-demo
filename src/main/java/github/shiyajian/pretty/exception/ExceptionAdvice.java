package github.shiyajian.pretty.exception;

import github.shiyajian.pretty.commons.AbstractServiceException;
import github.shiyajian.pretty.commons.ResponseVO;
import github.shiyajian.pretty.utils.I18nMessageUtil;
import github.shiyajian.pretty.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author shiyajian
 * create: 2019-01-27
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    // -------------------------- exceptions explain ------------------------------//
    // 异常分为系统异常和业务异常
    // 前台 ajax 访问，每次返回之后进行判断：
    // 如果 http status != 200 ，说明是系统异常
    // 比如服务器异常（500），应该跳转到 服务不可用的友好页面
    // 比如请求的文章找不到了（404），应该跳转到404页面，比如某些网站的404页面是个寻人启事啥的
    // 比如没有登录被拦截（401），应该弹出登录窗口
    // 如果 http status = 200 ,说明请求是完整请求的
    // 然后再检查统一返回对象（ResponseVO）的code,如果不为正常码，则alert（其中的message）


    // -------------------------- system exception --------------------------------//
    // 系统异常影响整个操作的流畅，比如访问时候，服务器挂了
    // 比如访问某个页面，没有权限
    // 比如访问某个页面，这个页面不存在
    // 比如访问某个接口，结果参数的格式和类型不对


    /**
     * 参数校验不通过异常处理
     * @param e validation 校验异常
     * @return 返回给前台的响应实体，会被Jackson序列化成json
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> validationExceptionHandler(MethodArgumentNotValidException e) {
        log.error("记录下异常，看是不是有人恶意请求接口测试", e);
        List<ObjectError> fieldErrors = e.getBindingResult().getAllErrors();
        String errorMsg = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        return ResponseEntity.of(Optional.of(errorMsg));
    }

    /**
     * 所有没有具体捕获的异常，最终都使用此方法处理
     * - 记录日志，方便后端进行日志跟踪，添加新的具体的异常处理
     * @param throwable 抛出的异常
     * @return 返回给前端一些信息
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handlerUnknownException(Throwable throwable) {
        log.error("服务器端错误", throwable);
        // 这里取决于要不要给前端暴露服务端的错误信息，很明显不应该暴露
        // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        // return new ResponseEntity<>(ResponseEnum.UNKNOWN.getText(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // -------------------------- business exception -----------------------------//
    // 返回值为200，返回给这次业务异常对应的信息
    // 比如：有个用户，打开一个列表10分钟一直未操作，这样他的数据属于延迟状态，在这10分钟内，如果有商家下架了某个产品
    // 这时候，应该告诉用户，此商品已下架，这种交互信息，属于业务异常。

    /**
     * 所有业务异常统一处理入口 （默认 HttpStatus.OK = 200）
     * @param exception 业务异常
     * @return 全局统一返回体
     */
    @ExceptionHandler(AbstractServiceException.class)
    public ResponseVO<String> handleServiceException(AbstractServiceException exception) {
        return ResponseUtil.failed(exception.getResponseEnum(),
                I18nMessageUtil.getMessage(exception.getKey(), exception.getArgs()));
    }
}
