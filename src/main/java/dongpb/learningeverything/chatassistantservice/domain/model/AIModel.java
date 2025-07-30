package dongpb.learningeverything.chatassistantservice.domain.model;

public interface AIModel {
    String getName();
    AIResponse chat(AIRequest request);
}
