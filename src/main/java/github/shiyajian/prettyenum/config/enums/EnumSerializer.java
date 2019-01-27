package github.shiyajian.prettyenum.config.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import github.shiyajian.prettyenum.pojo.enums.Enumerable;

import java.io.IOException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumSerializer extends StdSerializer<Enumerable> {


    public EnumSerializer(Class<Enumerable> type) {
        super(type);
    }

    @Override
    public void serialize(Enumerable enumerable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // 通过key,在i18n找对应枚举类的描述
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("value", enumerable.getValue());
        jsonGenerator.writeStringField("description", enumerable.getKey());
        jsonGenerator.writeEndObject();
    }
}
