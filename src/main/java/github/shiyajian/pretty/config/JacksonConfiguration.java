package github.shiyajian.pretty.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import github.shiyajian.pretty.config.enums.EnumDeserializer;
import github.shiyajian.pretty.config.enums.EnumSerializer;
import github.shiyajian.pretty.config.space.StringTrimDeserializer;
import github.shiyajian.pretty.commons.Enumerable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * 配置 Jackson ，使其支持枚举类的序列化和反序列化，通常有两处使用
 * 第一：接受前台 @RequestBody 传递过来的值，将其转换成 java 对象
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
    @SuppressWarnings({"rawtypes", "unchecked"})
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        SimpleModule customerModule = new SimpleModule();
        customerModule.addDeserializer(String.class, new StringTrimDeserializer(String.class));
        customerModule.addDeserializer(Enumerable.class, new EnumDeserializer(Enumerable.class));
        customerModule.addSerializer(Enumerable.class, new EnumSerializer(Enumerable.class));
        objectMapper.registerModule(customerModule);
        converter.setSupportedMediaTypes(ImmutableList.of(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        return converter;
    }

}
