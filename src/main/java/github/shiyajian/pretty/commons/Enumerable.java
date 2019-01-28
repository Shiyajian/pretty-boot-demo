package github.shiyajian.pretty.commons;

import github.shiyajian.pretty.utils.SpringContext;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

import static github.shiyajian.pretty.utils.ServletContext.request;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public interface Enumerable<E extends Enumerable> {

    /**
     * 获取在i18n文件中对应的 key
     */
    String getKey();

    /**
     * 获取最终保存到数据的值
     */
    int getValue();

    default String getDescription() {
        MessageSource messageSource = (MessageSource) SpringContext.getBean(MessageSource.class);
        Locale locale = RequestContextUtils.getLocale(request());
        return messageSource.getMessage(this.getKey(), null, locale);
    }
}
