package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.database.repositories.FunctionRepository;
import dongpb.learningeverything.chatassistantservice.database.repositories.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceInitService {
    private final ResourceRepository resourceRepository;
    private final FunctionRepository functionRepository;

    ResourceDTO getResourceByCode(String code){


        return null;
    }
}
