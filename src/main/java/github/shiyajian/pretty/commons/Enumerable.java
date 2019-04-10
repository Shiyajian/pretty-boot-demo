package github.shiyajian.pretty.commons;

import github.shiyajian.pretty.utils.I18nMessageUtil;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public interface Enumerable {

    /**
     * 获取在i18n文件中对应的 i18nKey
     * @return i18nKey
     */
    String getKey();

    /**
     * 获取最终保存到数据库的值
     * @return 值
     */
    int getValue();

    /**
     * 获取 i18nKey 对应的文本信息
     * @return 文本信息
     */
    default String getText() {
        return I18nMessageUtil.getMessage(this.getKey(), null);
    }
}
