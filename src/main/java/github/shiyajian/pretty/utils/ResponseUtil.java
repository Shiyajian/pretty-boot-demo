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

    public static <T> ResponseVO<T> success() {
        return new ResponseVO<>();
    }

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<>(data);
    }

    public static <T> ResponseVO<List<T>> success(PageDTO<T> data) {
        return new ResponseVO<>(data.getTotal(), data.getData());
    }

    public static <T> ResponseVO<T> failed(ResponseEnum response) {
        return new ResponseVO<>(response, false);
    }

    public static <T> ResponseVO<T> failed(ResponseEnum response, String msg) {
        return new ResponseVO<>(response, msg, false);
    }

}
