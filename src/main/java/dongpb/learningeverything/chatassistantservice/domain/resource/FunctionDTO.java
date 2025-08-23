package dongpb.learningeverything.chatassistantservice.domain.resource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import dongpb.learningeverything.chatassistantservice.domain.resource.http.HttpFunctionDTO;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "functionType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HttpFunctionDTO.class, name = "http")
})
public abstract class FunctionDTO {
    private Integer functionId;
    private String functionName;
    private String description;
    private String functionCode;
    private String resourceCode;

    public abstract String getFunctionType();
}
