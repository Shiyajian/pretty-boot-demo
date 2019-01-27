package github.shiyajian.prettyenum.config.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import github.shiyajian.prettyenum.pojo.enums.Enumerable;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumDeserializer<E extends Enumerable> extends StdDeserializer<E> {

    private Class<E> enumType;

    public EnumDeserializer(@NotNull Class<E> enumType) {
        super(enumType);
        this.enumType = enumType;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return EnumUtil.of(this.enumType, jsonParser.getValueAsString());
    }

}
