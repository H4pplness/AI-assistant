package dongpb.learningeverything.chatassistantservice.domain.mcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;
import dongpb.learningeverything.chatassistantservice.domain.model.AIResponse;
import dongpb.learningeverything.chatassistantservice.domain.model.AIService;
import dongpb.learningeverything.chatassistantservice.domain.resource.http.HttpFunctionService;
import dongpb.learningeverything.chatassistantservice.domain.resource.http.HttpRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Setter
@RequiredArgsConstructor
public class McpService {
    private final AIService aiService;
    private List<AIRequest.Message> prompt = McpInitPrompt.getInitMessagePrompt();
    private final HttpFunctionService httpFunctionService;

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
            String apiResponse = sendApi(map);
            AIRequest.Message systemMessage = AIRequest.Message.builder()
                    .role("user")
                    .content(apiResponse)
                    .build();

            additionalMessages.add(systemMessage);
            request.setMessages(additionalMessages);

            response = aiService.chat(request);
            jsonContent = (String) response.getContent();
            response.setContent(mapper.readValue(jsonContent, Map.class));
            return response;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String sendApi(Map<String,Object> request) throws Exception{
        if (request.containsKey("message")) {
            List<Object> messages = (List<Object>) request.get("message");
            Map<String,Object> message = (Map<String,Object>) messages.get(0);
            HttpRequestDTO httpRequestDTO = new HttpRequestDTO();

            httpRequestDTO.setUrl(message.get("url").toString());
            httpRequestDTO.setPath(message.get("path").toString());
            httpRequestDTO.setMethod(message.get("method").toString());
            httpRequestDTO.setQueryParams(message.get("queryParams") != null ? (Map<String,String>) message.get("queryParams") : null);

            Object response = httpFunctionService.sendRequest(httpRequestDTO);
            Map<String,Object> messageMap = new HashMap<>();
            messageMap.put("name",message.get("name"));
            messageMap.put("response",response);

            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("message",List.of(messageMap));
            responseMap.put("role","system");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(responseMap);

            return jsonString;
        }

        return null;
    }


}
