package dongpb.learningeverything.chatassistantservice.domain.mcp;

import lombok.Data;

import java.util.List;

@Data
public class FunctionCalling {
    private String name;
    private List<FunctionParameter> parameters;

    @Data
    public static class FunctionParameter{

    }
}
