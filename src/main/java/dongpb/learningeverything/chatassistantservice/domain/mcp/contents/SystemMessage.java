package dongpb.learningeverything.chatassistantservice.domain.mcp.contents;

import dongpb.learningeverything.chatassistantservice.domain.mcp.FunctionCalling;
import dongpb.learningeverything.chatassistantservice.domain.mcp.McpResponse;

import java.util.Map;

public class SystemMessage extends McpResponse<FunctionCalling> {
    @Override
    public String getMethod() {
        return "function-calling";
    }

    @Override
    public void setContent(Map<String,Object> response) {

    }
}
