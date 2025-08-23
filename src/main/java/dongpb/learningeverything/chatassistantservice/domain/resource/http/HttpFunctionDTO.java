package dongpb.learningeverything.chatassistantservice.domain.resource.http;

import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionType;
import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpFunctionDTO extends FunctionDTO {
    String baseUrl;
    String path;
    String method;
    Map<String, Object> requestBody;
    Map<String, String> headers;
    Map<String, String> queryParams;

    @Override
    public String getFunctionType() {
        return FunctionType.HTTP.getName();
    }
}
