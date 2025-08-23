package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.application.constants.ResponseConstant;
import dongpb.learningeverything.chatassistantservice.application.exception.BaseException;
import dongpb.learningeverything.chatassistantservice.application.exception.Errors;
import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;
import dongpb.learningeverything.chatassistantservice.database.entities.ResourceEntity;
import dongpb.learningeverything.chatassistantservice.database.repositories.FunctionRepository;
import dongpb.learningeverything.chatassistantservice.database.repositories.ResourceRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Setter
@Slf4j
public class ResourceServiceImpl implements ResourceService {
    private final Map<String, FunctionMapper> functionMapperMap;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    public ResourceServiceImpl(List<FunctionMapper> functionMappers) {
        functionMapperMap = new HashMap<>();
        for (FunctionMapper functionMapper : functionMappers) {
            functionMapperMap.put(functionMapper.getFunctionType(), functionMapper);
        }
    }

    @Override
    public String create(ResourceDTO resource) {
        if (resourceRepository.findByResourceCode(resource.getResourceCode()) != null) {
            log.error("Resource code {} is already exist", resource.getResourceCode());
            throw new BaseException(Errors.BAD_REQUEST);
        }

        ResourceEntity resourceEntity = new ResourceEntity();
        setBaseResourceEntity(resourceEntity,resource);

        resourceRepository.save(resourceEntity);

        List<FunctionEntity> functionEntities = new ArrayList<>();
        for (FunctionDTO functionDTO : resource.getFunctions()) {
            if (functionMapperMap.containsKey(functionDTO.getFunctionType())) {
                FunctionEntity functionEntity = functionMapperMap
                        .get(functionDTO.getFunctionType())
                        .functionDTOToFunctionEntity(functionDTO);
                functionEntities.add(functionEntity);
            } else {
                log.error("Function type {} is not supported", functionDTO.getFunctionType());
                throw new BaseException(Errors.BAD_REQUEST);
            }

            functionRepository.saveAll(functionEntities);
        }

        return ResponseConstant.SUCCESS;
    }

    private ResourceEntity setBaseResourceEntity(ResourceEntity resourceEntity,ResourceDTO resourceDTO) {
        resourceEntity.setResourceName(resourceDTO.getResourceName());
        resourceEntity.setDescription(resourceDTO.getDescription());
        resourceEntity.setResourceCode(resourceDTO.getResourceCode());
        return resourceEntity;
    }

    @Override
    public String update(ResourceDTO resource) {
        ResourceEntity resourceEntity = resourceRepository.findByResourceCode(resource.getResourceCode());
        validateUpdatedResource(resource, resourceEntity);

        updateResourceEntity(resource, resourceEntity);

        Map<String, FunctionEntity> updatedFunctionMap = functionRepository
                .findByResourceCode(resource.getResourceCode())
                .stream()
                .collect(Collectors.toMap(FunctionEntity::getFunctionCode, e -> e));

        List<FunctionEntity> functionEntities = new ArrayList<>();

        for (FunctionDTO functionDTO : resource.getFunctions()) {
            String functionType = functionDTO.getFunctionType();
            if (!functionMapperMap.containsKey(functionType)) {
                log.error("Function type {} is not supported", functionType);
                throw new BaseException(Errors.BAD_REQUEST);
            }

            if (functionDTO.getFunctionCode() == null) {
                FunctionEntity functionEntity = functionMapperMap
                        .get(functionType)
                        .functionDTOToFunctionEntity(functionDTO);

                functionEntities.add(functionEntity);
            } else {
                FunctionEntity functionEntity = updatedFunctionMap.get(functionDTO.getFunctionCode());
                if (functionEntity == null) {
                    log.error("Function code {} is not exist", functionDTO.getFunctionCode());
                    throw new BaseException(Errors.BAD_REQUEST);
                }

                functionMapperMap.get(functionType)
                                .updateFunctionEntityByFunctionDTO(functionEntity, functionDTO);

                functionEntities.add(functionEntity);
            }
        }

        functionRepository.saveAll(functionEntities);

        return ResponseConstant.SUCCESS;
    }

    public void validateUpdatedResource(ResourceDTO resource, ResourceEntity resourceEntity) {
        String resourceCode = resource.getResourceCode();
        if (resourceCode == null) {
            throw new BaseException(Errors.BAD_REQUEST);
        }

        if (resourceEntity == null) {
            throw new BaseException(Errors.BAD_REQUEST);
        }
    }

    public void updateResourceEntity(ResourceDTO resource, ResourceEntity resourceEntity) {
        resourceEntity.setResourceName(resource.getResourceName());
        resourceEntity.setDescription(resource.getDescription());

        resourceRepository.save(resourceEntity);
    }


    @Override
    public ResourceDTO getByCode(String code) {
        ResourceEntity resourceEntity = resourceRepository.findByResourceCode(code);
        if (resourceEntity == null) {
            throw new BaseException(Errors.NOT_FOUND_ENTITY, code);
        }

        ResourceDTO res = ResourceDTO.builder()
                .resourceCode(resourceEntity.getResourceCode())
                .resourceName(resourceEntity.getResourceName())
                .description(resourceEntity.getDescription())
                .build();

        List<FunctionEntity> functionEntities = functionRepository.findByResourceCode(code);

        List<FunctionDTO> functionDTOs = functionEntities
                .stream()
                .map(functionEntity ->{
                    String functionType = functionEntity.getFunctionType();
                    if (!functionMapperMap.containsKey(functionType)) {
                        throw new BaseException(Errors.BAD_REQUEST);
                    }
                    return functionMapperMap.get(functionType)
                            .functionEntityToFunctionDTO(functionEntity);
                }

                )
                .toList();

        res.setFunctions(functionDTOs);

        return res;
    }

    @Override
    public String delete(String code) {
        ResourceEntity resourceEntity = resourceRepository.findByResourceCode(code);
        if (resourceEntity == null) {
            throw new BaseException(Errors.NOT_FOUND_ENTITY, code);
        }

        resourceRepository.delete(resourceEntity);

        List<FunctionEntity> functionEntities = functionRepository.findByResourceCode(code);
        functionRepository.deleteAll(functionEntities);

        return ResponseConstant.SUCCESS;
    }
}
