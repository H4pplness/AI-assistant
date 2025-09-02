package dongpb.learningeverything.chatassistantservice.application.controllers;


import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;
import dongpb.learningeverything.chatassistantservice.domain.model.AIResponse;
import dongpb.learningeverything.chatassistantservice.domain.model.AIService;
import dongpb.learningeverything.chatassistantservice.domain.mcp.McpService;
import dongpb.learningeverything.chatassistantservice.domain.orchestration.AIAssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/chat")
@RequiredArgsConstructor
public class AssistantController {
    private final AIService aiService;
    private final McpService mcpService;
    private final AIAssistantService assistantService;

    @PostMapping("")
    public AIResponse chat(@RequestBody AIRequest request){
        return aiService.chat(request);
    }

    @PostMapping("/mcp")
    public AIResponse mcp(@RequestBody AIRequest request){
        return mcpService.chat(request);
    }

    @PostMapping("/assistant")
    public ResponseEntity<String> assistant(@RequestBody AIRequest request){
        return ResponseEntity.ok(assistantService.chat(request)) ;
    }

}
