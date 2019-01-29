package github.shiyajian.pretty.config.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import github.shiyajian.pretty.commons.Enumerable;
import github.shiyajian.pretty.utils.EnumUtil;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumDeserializer<E extends Enumerable> extends StdDeserializer<E> {

    private Class<E> enumType;

    public EnumDeserializer(@Nonnull Class<E> enumType) {
        super(enumType);
        this.enumType = enumType;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return EnumUtil.of(this.enumType, jsonParser.getIntValue());
    }

}
