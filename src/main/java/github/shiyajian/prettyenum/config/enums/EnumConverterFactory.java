package github.shiyajian.prettyenum.config.enums;

import github.shiyajian.prettyenum.pojo.enums.Enumerable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author shiyajian
 * create: 2019-01-22
 */
public class EnumConverterFactory implements ConverterFactory<String, Enumerable> {

    private final Map<Class, Converter> converterCache = new WeakHashMap<>();

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T extends Enumerable> Converter<String, T> getConverter(Class<T> targetType) {
        return converterCache.computeIfAbsent(targetType,
                k -> converterCache.put(k, new EnumConverter(k))
        );
    }

    protected class EnumConverter<T extends Enumerable> implements Converter<String, T> {

        private final Class<T> enumType;

        public EnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String value) {
            return EnumUtil.of(this.enumType, value);
        }
    }
}
