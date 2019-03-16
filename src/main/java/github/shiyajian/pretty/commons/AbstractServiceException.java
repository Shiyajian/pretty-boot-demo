package github.shiyajian.pretty.commons;

import lombok.Data;

/**
 * 所有业务异常父类
 * @author shiyajian
 * create: 2019-01-29
 */
@Data
public abstract class AbstractServiceException extends RuntimeException {

    /**
     * i18n 文件中对应的key
     */
    protected final String i18nKey;

    /**
     * i18n 文件中的对应文本信息可以是占位符，比如：hello {0} ,welcome {1}
     */
    protected final Object[] args;

    /**
     * 无参构造，异常拦截全部从 ResponseStatusEnum 中获得
     */
    public AbstractServiceException() {
        this.i18nKey = null;
        this.args = null;
    }

    /**
     * 覆盖 ResponseStatusEnum 中的错误信息
     * @param i18nKey  国际化资源文件中对应的key
     * @param args 参数
     */
    public AbstractServiceException(String i18nKey, Object... args) {
        this.i18nKey = i18nKey;
        this.args = args;
    }

    /**
     * 返回异常实现类对应的业务异常
     * @return 异常枚举
     */
    public abstract ResponseStatusEnum getResponseEnum();
}
