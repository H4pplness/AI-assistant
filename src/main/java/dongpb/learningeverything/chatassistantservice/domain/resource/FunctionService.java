package dongpb.learningeverything.chatassistantservice.domain.resource;

import org.springframework.stereotype.Service;

@Service
public interface FunctionService<T extends FunctionDTO> {
    Object sendRequest(T functionDTO);
    String getType();
}
