package github.shiyajian.pretty.commons;

import lombok.Data;

import javax.annotation.Nullable;

/**
 * 统一返回实体
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
        this(ResponseEnum.SUCCESS, true, 0L, null);
    }

    public ResponseVO(T data) {
        this(ResponseEnum.SUCCESS, true, 0L, data);
    }

    public ResponseVO(ResponseEnum responseEnum) {
        this(responseEnum, true, 0L, null);
    }

    public ResponseVO(ResponseEnum responseEnum, boolean succeeded) {
        this(responseEnum, succeeded, 0L, null);
    }

    public ResponseVO(Long total, T data) {
        this(ResponseEnum.SUCCESS, true, total, data);
    }

    public ResponseVO(ResponseEnum responseEnum, Boolean succeeded, Long total, T data) {
        this.code = responseEnum.getValue();
        this.msg = responseEnum.getDescription();
        this.succeeded = succeeded;
        this.total = total;
        this.data = data;
    }


    @Nullable
    public T get() {
        return this.data;
    }
}
