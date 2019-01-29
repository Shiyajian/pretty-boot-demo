package github.shiyajian.pretty.commons;

import lombok.Data;

import javax.annotation.Nullable;

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
     * 统一返回状态码对应的描述
     */
    private String error;

    /**
     * 详细的错误提示
     */
    private String msg;

    /**
     * 是否成功
     */
    private Boolean succeeded;

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
        this(ResponseEnum.SUCCESS, null, true, null, null);
    }

    public ResponseVO(T data) {
        this(ResponseEnum.SUCCESS, null, true, null, data);
    }

    public ResponseVO(ResponseEnum responseEnum, boolean succeeded) {
        this(responseEnum, null, succeeded, null, null);
    }

    public ResponseVO(ResponseEnum responseEnum, String msg, boolean succeeded) {
        this(responseEnum, msg, succeeded, null, null);
    }

    public ResponseVO(Long total, T data) {
        this(ResponseEnum.SUCCESS, null, true, total, data);
    }

    public ResponseVO(ResponseEnum responseEnum, String msg, Boolean succeeded, Long total, T data) {
        this(responseEnum.getText(), responseEnum.getValue(), msg, succeeded, total, data);
    }

    public ResponseVO(String error, Integer code, String msg, Boolean succeeded, Long total, T data) {
        this.error = error;
        this.code = code;
        this.msg = msg;
        this.succeeded = succeeded;
        this.total = total;
        this.data = data;
    }


    @Nullable
    public T get() {
        return this.data;
    }
}
