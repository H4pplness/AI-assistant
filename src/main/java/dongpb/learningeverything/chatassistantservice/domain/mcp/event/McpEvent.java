package dongpb.learningeverything.chatassistantservice.domain.mcp.event;

import lombok.Data;

@Data
public abstract class McpEvent<T> {
    private String role;
    private T content;

    abstract public String getMethod();
    abstract public Object handle();
}
