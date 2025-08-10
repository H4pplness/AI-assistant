package dongpb.learningeverything.chatassistantservice.domain.mcp.contents;

import dongpb.learningeverything.chatassistantservice.domain.mcp.AssistantResponse;
import dongpb.learningeverything.chatassistantservice.domain.mcp.McpResponse;

import java.util.Map;

public class AssistantMessage extends McpResponse<AssistantResponse> {
    @Override
    public String getMethod() {
        return "response";
    }

    @Override
    public void setContent(Map<String, Object> response) {

    }
}
