package dongpb.learningeverything.chatassistantservice.domain.resource.http;

import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;
import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpFunctionMapper implements FunctionMapper<HttpFunctionDTO> {
    @Override
    public HttpFunctionDTO functionEntityToFunctionDTO(FunctionEntity functionEntity) {
        HttpFunctionDTO functionDTO = new HttpFunctionDTO();
        functionDTO.setFunctionId(functionEntity.getFunctionId());
        functionDTO.setFunctionName(functionEntity.getFunctionName());
        functionDTO.setDescription(functionEntity.getDescription());
        functionDTO.setFunctionCode(functionEntity.getFunctionCode());
        functionDTO.setResourceCode(functionEntity.getResourceCode());

        Map<String, Object> metadata = functionEntity.getMetadata();

        if (metadata != null) {
            if (metadata.containsKey("baseUrl")) {
                functionDTO.setBaseUrl((String) metadata.get("baseUrl"));
            }

            if (metadata.containsKey("path")) {
                functionDTO.setPath((String) metadata.get("path"));
            }

            if (metadata.containsKey("method")) {
                functionDTO.setMethod((String) metadata.get("method"));
            }

            if (metadata.containsKey("requestBody")) {
                functionDTO.setRequestBody((Map<String, Object>) metadata.get("requestBody"));
            }

            if (metadata.containsKey("headers")) {
                functionDTO.setHeaders((Map<String, String>) metadata.get("headers"));
            }

            if (metadata.containsKey("queryParams")) {
                functionDTO.setQueryParams((Map<String, String>) metadata.get("queryParams"));
            }
        }

        return functionDTO;
    }

    @Override
    public FunctionEntity functionDTOToFunctionEntity(HttpFunctionDTO functionDTO) {
        FunctionEntity functionEntity = new FunctionEntity();

        functionEntity.setFunctionId(functionDTO.getFunctionId());
        functionEntity.setFunctionName(functionDTO.getFunctionName());
        functionEntity.setDescription(functionDTO.getDescription());
        functionEntity.setFunctionCode(functionDTO.getFunctionCode());
        functionEntity.setResourceCode(functionDTO.getResourceCode());
        functionEntity.setFunctionType(functionDTO.getFunctionType());

        functionEntity.setMetadata(getMetadataFromDTO(functionDTO));

        return functionEntity;
    }

    /**
     * same as HttpFunctionDTO.getFunctionType
     * @return
     */
    @Override
    public String getFunctionType() {
        return "http";
    }

    @Override
    public void updateFunctionEntityByFunctionDTO(FunctionEntity functionEntity, HttpFunctionDTO functionDTO) {
        functionEntity.setFunctionName(functionDTO.getFunctionName());
        functionEntity.setDescription(functionDTO.getDescription());
        functionEntity.setMetadata(getMetadataFromDTO(functionDTO));
    }


    private Map<String,Object> getMetadataFromDTO(HttpFunctionDTO functionDTO) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("baseUrl", functionDTO.getBaseUrl());
        metadata.put("path", functionDTO.getPath());
        metadata.put("method", functionDTO.getMethod());
        metadata.put("requestBody", functionDTO.getRequestBody());
        metadata.put("headers", functionDTO.getHeaders());
        metadata.put("queryParams", functionDTO.getQueryParams());

        return metadata;
    }
}
