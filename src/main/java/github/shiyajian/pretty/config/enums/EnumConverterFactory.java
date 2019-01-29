package github.shiyajian.pretty.config.enums;

import github.shiyajian.pretty.commons.Enumerable;
import github.shiyajian.pretty.utils.EnumUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import javax.annotation.Nonnull;
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
    public <T extends Enumerable> Converter<String, T> getConverter(@Nonnull Class<T> targetType) {
        return converterCache.computeIfAbsent(targetType,
                k -> converterCache.put(k, new EnumConverter(k))
        );
    }

    protected class EnumConverter<T extends Enumerable> implements Converter<Integer, T> {

        private final Class<T> enumType;

        public EnumConverter(@Nonnull Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(@Nonnull Integer value) {
            return EnumUtil.of(this.enumType, value);
        }
    }
}
