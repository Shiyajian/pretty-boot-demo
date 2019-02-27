package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.commons.PageDTO;
import github.shiyajian.pretty.commons.ResponseEnum;
import github.shiyajian.pretty.commons.ResponseVO;

import java.util.List;

/**
 * @author shiyajian
 * create: 2018-10-24
 */
public class ResponseUtil {

    private ResponseUtil() { /* no instance */ }

    public static <T> ResponseVO<T> ok() {
        return new ResponseVO<>();
    }

    public static <T> ResponseVO<T> ok(T data) {
        return new ResponseVO<>(data);
    }

    public static <T> ResponseVO<T> ok(T data, String msgKey, Object... args) {
        return new ResponseVO<>(data, I18nMessageUtil.getMessage(msgKey, args));
    }

    public static <T> ResponseVO<List<T>> ok(PageDTO<T> data) {
        return new ResponseVO<>(data.getTotal(), data.getData());
    }

    public static <T> ResponseVO<T> failed(ResponseEnum response) {
        return new ResponseVO<>(response);
    }

    public static <T> ResponseVO<T> failed(ResponseEnum response, String msg) {
        return new ResponseVO<>(response, msg);
    }

}
