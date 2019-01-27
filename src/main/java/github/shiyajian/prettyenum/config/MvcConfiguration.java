package github.shiyajian.prettyenum.config;

import github.shiyajian.prettyenum.config.enums.EnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 对于没有在 body 中传递的数据，例如【GET】请求，参数在URL中传递，使用的 url_encoded 编码格式
 * 通过本类使其支持枚举类转换
 * @author shiyajian
 * create: 2019-01-27
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

}
