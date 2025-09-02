package dongpb.learningeverything.chatassistantservice.domain.mcp.event;

import dongpb.learningeverything.chatassistantservice.domain.mcp.MethodType;
import dongpb.learningeverything.chatassistantservice.domain.mcp.contents.AssistantContent;

public class FunctionCallingEvent extends McpEvent<AssistantContent>{
    @Override
    public String getMethod() {
        return MethodType.FUNCTION_CALLING.getMethod();
    }

    @Override
    public Object handle() {
        return null;
    }
}
