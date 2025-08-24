package dongpb.learningeverything.chatassistantservice.domain.resource;

import org.springframework.stereotype.Service;

@Service
public interface FunctionService<T extends RequestDTO> {
    Object sendRequest(T request);
    String getType();
}
