package dongpb.learningeverything.chatassistantservice.domain.resource;

public enum FunctionType {
    HTTP("http"),

    ;

    private FunctionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private final String name;


}
