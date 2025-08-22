package dongpb.learningeverything.chatassistantservice.domain.mcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;
import dongpb.learningeverything.chatassistantservice.domain.model.AIResponse;
import dongpb.learningeverything.chatassistantservice.domain.model.AIService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Setter
@RequiredArgsConstructor
public class McpService {
    private final AIService aiService;
    private List<AIRequest.Message> prompt = McpInitPrompt.getInitMessagePrompt();

    public AIResponse chat(AIRequest request){
        List<AIRequest.Message> messages = request.getMessages();
        List<AIRequest.Message> additionalMessages = new ArrayList<>();
        // add init prompt to context
        additionalMessages.addAll(prompt);
        additionalMessages.addAll(messages);
        request.setMessages(additionalMessages);

        AIResponse response = aiService.chat(request);
        String jsonContent = (String) response.getContent();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map map = mapper.readValue(jsonContent, Map.class);
            response.setContent(map);
            return response;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }



}
