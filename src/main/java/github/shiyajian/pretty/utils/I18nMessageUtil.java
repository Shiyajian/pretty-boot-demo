package github.shiyajian.pretty.utils;

import github.shiyajian.pretty.holder.SpringContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

import static github.shiyajian.pretty.holder.ServletContextHolder.request;

/**
 * 国际化资源工具类
 * @author shiyajian
 * create: 2019-01-29
 */
public final class I18nMessageUtil {

    private I18nMessageUtil() { /* no instance */ }

    /**
     * 根据key和参数获取对应的内容信息
     * @param key  在国际化资源文件中对应的key
     * @param args 参数
     * @return 对应的内容信息
     */
    public static String getMessage(@Nonnull String key, @Nullable Object[] args) {
        MessageSource messageSource = SpringContextHolder.getBean(MessageSource.class);
        Locale locale = RequestContextUtils.getLocale(request());
        return messageSource.getMessage(key, args, locale);
    }
}
