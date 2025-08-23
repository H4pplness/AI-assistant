package dongpb.learningeverything.chatassistantservice.domain.resource;

import dongpb.learningeverything.chatassistantservice.database.entities.FunctionEntity;


public interface FunctionMapper<T extends FunctionDTO> {
    T functionEntityToFunctionDTO(FunctionEntity functionEntity);
    FunctionEntity functionDTOToFunctionEntity(T functionDTO);
    String getFunctionType();
    void updateFunctionEntityByFunctionDTO(FunctionEntity functionEntity, T functionDTO);
}
