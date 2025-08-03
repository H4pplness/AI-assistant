package dongpb.learningeverything.chatassistantservice.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AIRequest {
    private String model;
    private List<Message> messages;
    private Map<String, Object> metadata;

    @Data
    @Builder
    public static class Message{
        private String role;
        private String content;
    }
}
