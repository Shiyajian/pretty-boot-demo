package github.shiyajian.pretty.commons;

/**
 * 业务返回状态枚举类
 * @author shiyajian
 * create: 2018-10-24
 */
public enum ResponseStatusEnum implements Enumerable {

    /** 请求成功 */
    SUCCESS(20000, "enum.response.success"),

    /** 未知状态 */
    UNKNOWN(20500, "enum.response.unknown"),

    /** 错误的请求参数 */
    ILLEGAL_ARGUMENT(20300, "enum.response.illegal-argument"),

    /** 错误的状态 */
    ILLEGAL_STATE(20200, "enum.response.illegal-state"),

    /** 没有授权 */
    UNAUTHORIZED(20401, "enum.response.unauthorized"),

    /** 没找到对应的资源 */
    NOT_FOUND(20404, "enum.response.not-found"),

    /** 服务熔断降级 */
    FALLBACK(20500, "enum.response.fallback");

    private int value;

    private String key;

    ResponseStatusEnum(int value, String key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
