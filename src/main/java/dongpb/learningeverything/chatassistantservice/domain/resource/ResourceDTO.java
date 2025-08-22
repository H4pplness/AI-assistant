package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.domain.resource.function_type.FunctionDTO;
import dongpb.learningeverything.chatassistantservice.domain.resource.function_type.HttpFunctionDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResourceDTO {
    private String resourceCode;
    private String resourceName;
    private String description;

    private List<FunctionDTO> functions;
}
