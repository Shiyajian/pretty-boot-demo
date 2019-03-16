package github.shiyajian.pretty.commons;

import github.shiyajian.pretty.utils.I18nMessageUtil;
import lombok.Data;

/**
 * 统一返回实体,全局使用包装类，如果结果为null，jackson不会传输，减少容量，美化结果
 * @author shiyajian
 * create: 2018-10-24
 */
@Data
public class ResponseVO<T> {


    /**
     * 统一返回状态码
     */
    private Integer code;

    /**
     * 详细的错误提示
     */
    private String msg;

    /**
     * 如果是分页数据，表示总数据条数；
     * 如果不是分页，则为0
     */
    private Long total;

    /**
     * 数据，如果是分页，则为list
     */
    private T data;

    public ResponseVO() {
        this(null);
    }

    public ResponseVO(T data) {
        this(null, data);
    }

    public ResponseVO(T data, String message) {
        this(null, data, message);
    }

    public ResponseVO(Long total, T data) {
        this(total, data, I18nMessageUtil.getMessage(ResponseStatusEnum.SUCCESS.getKey(), null));
    }

    public ResponseVO(Long total, T data, String message) {
        this(ResponseStatusEnum.SUCCESS.getValue(), message, total, data);
    }

    public ResponseVO(Integer code,String msg){
        this(code, msg, null, null);
    }

    public ResponseVO(Integer code, String msg, Long total, T data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

}
