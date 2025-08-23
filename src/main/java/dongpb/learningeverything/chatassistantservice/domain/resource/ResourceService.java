package dongpb.learningeverything.chatassistantservice.domain.resource;

import org.springframework.stereotype.Service;

@Service
public interface ResourceService {
    String update(ResourceDTO resourceDTO);
    ResourceDTO getByCode(String code);
    String delete(String code);
    String create(ResourceDTO resourceDTO);
}
