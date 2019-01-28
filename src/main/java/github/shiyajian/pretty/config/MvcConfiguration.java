package github.shiyajian.pretty.config;

import github.shiyajian.pretty.config.enums.EnumConverterFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 对于没有在 body 中传递的数据，例如【GET】请求，参数在URL中传递，使用的 url_encoded 编码格式
 * 通过本类使其支持枚举类转换
 * @author shiyajian
 * create: 2019-01-27
 */
@Configuration
@ControllerAdvice
public class MvcConfiguration implements WebMvcConfigurer, WebBindingInitializer {

    /**
     * [get]请求中，将int值转换成枚举类
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

    /**
     * [GET]请求下，将所有参数的空格trim掉
     * 如果前台必须保留空格，前后空格请用%20转移
     * @param webDataBinder 数据绑定器
     */
    @Override
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
