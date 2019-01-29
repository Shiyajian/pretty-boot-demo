package github.shiyajian.pretty.config.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import github.shiyajian.pretty.commons.Enumerable;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class EnumSerializer extends StdSerializer<Enumerable> {

    public EnumSerializer(@Nonnull Class<Enumerable> type) {
        super(type);
    }

    @Override
    public void serialize(Enumerable enumerable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("value", enumerable.getValue());
        jsonGenerator.writeStringField("text", enumerable.getText());
        jsonGenerator.writeEndObject();
    }
}
