package github.shiyajian.pretty.config.space;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * 如果请求方法不是[GET]，而是封装在 body 中，调用此转换器，将空格trim掉
 * @author shiyajian
 * create: 2019-01-27
 */
public class StringTrimDeserializer extends StdDeserializer<String> {

    public StringTrimDeserializer(Class<?> classType) {
        super(classType);
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return jsonParser.getValueAsString().trim();
    }
}
