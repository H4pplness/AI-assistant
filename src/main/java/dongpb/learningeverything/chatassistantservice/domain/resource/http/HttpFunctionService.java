package dongpb.learningeverything.chatassistantservice.domain.resource.http;

import dongpb.learningeverything.chatassistantservice.application.exception.BaseException;
import dongpb.learningeverything.chatassistantservice.application.exception.Errors;
import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionType;
import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HttpFunctionService implements FunctionService<HttpFunctionDTO> {

    @Autowired
    private final RestTemplate restTemplate;

    @Override
    public Object sendRequest(HttpFunctionDTO httpFunctionDTO) {
        switch (httpFunctionDTO.getMethod().toUpperCase()){
            case "GET" : {
                ResponseEntity<Object> responseEntity = restTemplate.getForEntity(
                        httpFunctionDTO.getBaseUrl() + httpFunctionDTO.getPath(),
                        Object.class,
                        httpFunctionDTO.getRequestBody());

                Map<String, String> requestParams = new HashMap<>();


                return responseEntity.getBody();
            }

            default:
                throw new BaseException(Errors.BAD_REQUEST);
        }
    }

    @Override
    public String getType() {
        return FunctionType.HTTP.getName();
    }
}
