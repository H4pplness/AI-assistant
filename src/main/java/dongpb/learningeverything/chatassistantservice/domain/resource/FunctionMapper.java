package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;
import dongpb.learningeverything.chatassistantservice.domain.resource.function_type.FunctionDTO;


public interface FunctionMapper<T extends FunctionDTO> {
    T functionEntityToFunctionDTO(FunctionEntity functionEntity);
    FunctionEntity functionDTOToFunctionEntity(T functionDTO);
    String getFunctionType();
}
