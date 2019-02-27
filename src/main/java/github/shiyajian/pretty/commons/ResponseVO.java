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
     * 如果是分页数据，表示总数据条数；
     * 如果不是分页，则为0
     */
    private Long total;

    /**
     * 数据，如果是分页，则为list
     */
    private T data;

    /**
     * 响应时间
     */
    private Long timestamp;

    public ResponseVO() {
        this(ResponseEnum.SUCCESS, null, null, null);
    }

    public ResponseVO(T data) {
        this(ResponseEnum.SUCCESS, null, null, data);
    }

    public ResponseVO(T data, String msg) {
        this(ResponseEnum.SUCCESS, msg, null, data);
    }

    public ResponseVO(ResponseEnum responseEnum) {
        this(responseEnum, null, null, null);
    }

    public ResponseVO(ResponseEnum responseEnum, String msg) {
        this(responseEnum, msg, null, null);
    }

    public ResponseVO(Long total, T data) {
        this(ResponseEnum.SUCCESS, null, total, data);
    }

    public ResponseVO(ResponseEnum responseEnum, String msg, Long total, T data) {
        this(responseEnum.getText(), responseEnum.getValue(), msg, total, data);
    }

    public ResponseVO(String error, Integer code, String msg, Long total, T data) {
        this.error = error;
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

}
