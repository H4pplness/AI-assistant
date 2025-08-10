package dongpb.learningeverything.chatassistantservice.domain.mcp;

import dongpb.learningeverything.chatassistantservice.domain.model.AIResponse;
import lombok.Data;

import java.util.Map;

@Data
public abstract class McpResponse<T> {
    String role;
    T content;

    abstract public String getMethod();
    abstract public void setContent(Map<String,Object> response);
}