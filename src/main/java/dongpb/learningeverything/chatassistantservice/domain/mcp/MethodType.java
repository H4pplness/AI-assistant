package dongpb.learningeverything.chatassistantservice.domain.mcp;

public enum MethodType {
    FUNCTION_CALLING, SYSTEM_EVENT, USER_EVENT;

    public String getMethod() {
        return this.name().toLowerCase();
    }
}
