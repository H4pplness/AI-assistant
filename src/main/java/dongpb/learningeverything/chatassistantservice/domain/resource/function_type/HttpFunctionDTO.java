package dongpb.learningeverything.chatassistantservice.domain.resource.function_type;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpFunctionDTO extends FunctionDTO{
    String baseUrl;
    String path;
    String method;
    Map<String, Object> requestBody;
    Map<String, String> headers;
    Map<String, String> queryParams;

    @Override
    public String getFunctionType() {
        return "http";
    }

    @Override
    public Object sendRequest() {
        return null;
    }
}
