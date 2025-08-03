package dongpb.learningeverything.chatassistantservice.domain.mcp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dongpb.learningeverything.chatassistantservice.application.exception.BaseException;
import dongpb.learningeverything.chatassistantservice.application.exception.Errors;
import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class McpInitPrompt {
    @Getter
    private static final List<AIRequest.Message> initMessagePrompt = new ArrayList<>();

    public McpInitPrompt(){
        ClassPathResource contextResource = new ClassPathResource("mcp_prompt.txt");
        String contextContent;
        try {
            contextContent = Files.readString(contextResource.getFile().toPath());
        }catch (IOException e){
            throw new BaseException(Errors.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        AIRequest.Message context = AIRequest.Message.builder()
                .role("user")
                .content(contextContent).build();
        AIRequest.Message assistantResponse = AIRequest.Message.builder()
                .role("assistant")
                .content("{ \"model\": \"groq\", \"messages\": [ { \"role\": \"assistant\", \"content\": \"Xin chào, tôi có thể giúp gì cho bạn ?\" } ] }").build();

        initMessagePrompt.add(context);
        initMessagePrompt.add(assistantResponse);
    }

}

