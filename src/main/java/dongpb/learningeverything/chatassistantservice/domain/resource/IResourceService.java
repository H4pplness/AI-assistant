package dongpb.learningeverything.chatassistantservice.domain.resource;

public interface IResourceService {
    String update(ResourceDTO resourceDTO);
    ResourceDTO getByCode(String code);
    String delete(String code);
    String create(ResourceDTO resourceDTO);
}
