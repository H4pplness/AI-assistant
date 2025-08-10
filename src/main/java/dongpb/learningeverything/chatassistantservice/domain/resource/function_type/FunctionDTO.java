package dongpb.learningeverything.chatassistantservice.domain.resource.function_type;

public abstract class FunctionDTO {
    Integer functionId;
    String functionName;
    String description;

    public abstract String getFunctionType();
    public abstract Object sendRequest();
}
