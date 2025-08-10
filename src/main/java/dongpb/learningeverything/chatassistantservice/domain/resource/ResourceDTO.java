package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.domain.resource.function_type.HttpFunctionDTO;
import lombok.Data;

import java.util.List;

@Data
public class ResourceDTO {
    private String resourceId;
    private String resourceName;
    private String baseUrl;
    private String description;

    private List<HttpFunctionDTO> functions;
}
