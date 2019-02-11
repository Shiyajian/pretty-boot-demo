package github.shiyajian.pretty.commons;

import github.shiyajian.pretty.utils.I18nMessageUtil;

import javax.annotation.Nonnull;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public interface Enumerable<E extends Enumerable> {

    /**
     * 获取在i18n文件中对应的 key
     * @return key
     */
    @Nonnull
    String getKey();

    /**
     * 获取最终保存到数据库的值
     * @return 值
     */
    @Nonnull
    int getValue();

    /**
     * 获取 key 对应的文本信息
     * @return 文本信息
     */
    @Nonnull
    default String getText() {
        return I18nMessageUtil.getMessage(this.getKey(), null);
    }
}
