package dongpb.learningeverything.chatassistantservice.domain.resource;

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
