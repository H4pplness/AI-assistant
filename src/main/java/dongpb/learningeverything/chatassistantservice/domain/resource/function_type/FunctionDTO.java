package dongpb.learningeverything.chatassistantservice.domain.resource.function_type;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
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
    public abstract Object sendRequest();
}
