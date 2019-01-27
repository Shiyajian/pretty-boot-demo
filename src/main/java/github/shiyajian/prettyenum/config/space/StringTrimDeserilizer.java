package github.shiyajian.prettyenum.config.space;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @author shiyajian
 * create: 2019-01-27
 */
public class StringTrimDeserilizer extends StdDeserializer<String> {

    public StringTrimDeserilizer(Class<?> classType) {
        super(classType);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return jsonParser.getValueAsString().trim();
    }
}
