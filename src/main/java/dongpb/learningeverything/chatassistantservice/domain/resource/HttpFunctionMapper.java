package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;
import dongpb.learningeverything.chatassistantservice.domain.resource.function_type.HttpFunctionDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpFunctionMapper implements FunctionMapper<HttpFunctionDTO> {
    @Override
    public HttpFunctionDTO functionEntityToFunctionDTO(FunctionEntity functionEntity) {
        return null;
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

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("baseUrl", functionDTO.getBaseUrl());
        metadata.put("path", functionDTO.getPath());
        metadata.put("method", functionDTO.getMethod());
        metadata.put("requestBody", functionDTO.getRequestBody());
        metadata.put("headers", functionDTO.getHeaders());
        metadata.put("queryParams", functionDTO.getQueryParams());

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
}
