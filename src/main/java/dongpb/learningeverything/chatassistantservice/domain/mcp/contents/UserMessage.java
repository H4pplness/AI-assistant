package dongpb.learningeverything.chatassistantservice.domain.mcp.contents;

import dongpb.learningeverything.chatassistantservice.domain.mcp.McpResponse;

import java.util.Map;

public class UserMessage extends McpResponse<String> {
    @Override
    public String getMethod() {
        return "user-response";
    }

    @Override
    public void setContent(Map<String,Object> response) {

    }
}
