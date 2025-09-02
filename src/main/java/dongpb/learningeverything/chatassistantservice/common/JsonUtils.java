package dongpb.learningeverything.chatassistantservice.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JsonUtils {
    private static String extractJson(String text) {
        int first = text.indexOf('{');
        int last = text.lastIndexOf('}');
        if (first >= 0 && last > first) return text.substring(first, last + 1);
        return text;
    }

    public static Map<String,Object> toMap(String text) {
        text = extractJson(text);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(text, Map.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
