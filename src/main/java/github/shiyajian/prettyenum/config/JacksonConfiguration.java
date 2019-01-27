package github.shiyajian.prettyenum.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import github.shiyajian.prettyenum.pojo.enums.Enumerable;
import github.shiyajian.prettyenum.config.enums.EnumDeserializer;
import github.shiyajian.prettyenum.config.enums.EnumSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 配置 Jackson ，使其支持枚举类的序列化和反序列化，通常有两处使用
 * 第一：我们将 body 中的参数以 json 的形式，从前台传递到后台，最后调用 Jackson 转换成 Java 实体
 * 第二：我们将后台的实体Bean，通过 @ResponseBody 注解返回给前台
 */
@Configuration
@Slf4j
public class JacksonConfiguration {

    /**
     * Jackson的转换器
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(HttpMessageConverter.class)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule customerModule = new SimpleModule();
        customerModule.addDeserializer(Enumerable.class, new EnumDeserializer(Enumerable.class));
        customerModule.addSerializer(Enumerable.class, new EnumSerializer(Enumerable.class));
        objectMapper.registerModule(customerModule);
        converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        return converter;
    }

}
