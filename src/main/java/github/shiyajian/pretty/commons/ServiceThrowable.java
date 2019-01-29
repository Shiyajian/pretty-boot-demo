package github.shiyajian.pretty.commons;

import lombok.Data;

/**
 * 所有业务异常父类
 * @author shiyajian
 * create: 2019-01-29
 */
@Data
public abstract class ServiceThrowable extends RuntimeException {

    /**
     * i18n 文件中对应的key
     */
    protected final String key;

    /**
     * i18n 文件中的对应文本信息可以是占位符，比如：hello {0} ,welcome {1}
     */
    protected Object[] args;

    public ServiceThrowable(String key, Object... args) {
        this.key = key;
        this.args = args;
    }

    abstract public ResponseEnum getResponseEnum();
}
