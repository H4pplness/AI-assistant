package dongpb.learningeverything.chatassistantservice.domain.orchestration;

public class Step {
    private NodeType type;
//    private String

    public enum NodeType { MODEL_CALL, TOOL_CALL, FORMAT }

}


