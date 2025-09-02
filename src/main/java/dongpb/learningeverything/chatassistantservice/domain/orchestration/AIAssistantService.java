package dongpb.learningeverything.chatassistantservice.domain.orchestration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dongpb.learningeverything.chatassistantservice.common.JsonUtils;
import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;
import dongpb.learningeverything.chatassistantservice.domain.model.AIResponse;
import dongpb.learningeverything.chatassistantservice.domain.model.AIService;
import dongpb.learningeverything.chatassistantservice.domain.tool.ToolRequest;
import dongpb.learningeverything.chatassistantservice.domain.tool.ToolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIAssistantService {
    private final AIService aiService;
    private final ToolService toolService;


    public String chat(AIRequest request) {
        AIRequest prePrompt = InitPrompt.initializeRequest();
        prePrompt.addMessages(request.getMessages());
        prePrompt.setModel(request.getModel());

        AIResponse response = aiService.chat(prePrompt);
        Map<String,Object> responseContent = JsonUtils.toMap(response.getContent().toString());
        if (!responseContent.containsKey("message")){
            return "Hệ thống đang bị lỗi";
        }
        String method;
        if (responseContent.containsKey("method")) {
            method = responseContent.get("method").toString();
        }else {
            method = "response";
        }

        switch (method) {
            case "tool" : {
                try {
                    Map<String,Object> toolRequest = (Map) responseContent.get("message");
                    Map<String,Object> toolResponse = useTool(toolRequest);
                    ObjectMapper objectMapper = new ObjectMapper();

                    Map<String,Object> systemResponseMap = new HashMap<>();
                    systemResponseMap.put("role","system");
                    systemResponseMap.put("message",toolResponse);

                    String toolResponseJson = objectMapper.writeValueAsString(systemResponseMap);
                    AIRequest.Message systemResponse = AIRequest.Message
                            .builder()
                            .role("user")
                            .content(toolResponseJson)
                            .build();

                    AIRequest.Message aiRequest = AIRequest.Message
                            .builder()
                            .role("assistant")
                            .content(objectMapper.writeValueAsString(toolRequest))
                            .build();

                    prePrompt.addMessage(aiRequest);
                    prePrompt.addMessage(systemResponse);

                    response = aiService.chat(prePrompt);
                    responseContent = JsonUtils.toMap(response.getContent().toString());
                    return responseContent.get("message").toString();
                }catch (Exception e){
                    log.error("Use tool error: " + e.getMessage());
                    return "Hệ thống đang bị lỗi";
                }
            }
            case "response" : {
                return responseContent.get("message").toString();
            }
            default:
                log.error("Unknown method: " + method);
                return "Hệ thống đang bị lỗi";
        }
    }

    private Map<String,Object> useTool(Map<String,Object> content){
        ToolRequest toolRequest = convertToToolRequest(content);
        return toolService.execute(toolRequest);
    }

    private ToolRequest convertToToolRequest(Map<String,Object> content){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.convertValue(content, ToolRequest.class);
        }catch (Exception e){
            log.error("Convert tool request error: " + e.getMessage());
            return null;
        }
    }
}
