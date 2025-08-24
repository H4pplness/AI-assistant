package dongpb.learningeverything.chatassistantservice.domain.resource.http;

import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionType;
import dongpb.learningeverything.chatassistantservice.domain.resource.FunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class HttpFunctionService implements FunctionService<HttpRequestDTO> {

    @Autowired
    private final RestTemplate restTemplate;

    @Override
    public Object sendRequest(HttpRequestDTO request) {
        switch (request.getMethod()) {
            case "GET" : {
                return sendGetRequest(request);
            }
            case "POST": {

            }
            default:
                throw new RuntimeException("Not support method: " + request.getMethod());
        }
    }

    private Object sendGetRequest(HttpRequestDTO request){
        String url = request.getUrl() + "/" + request.getPath();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        if (request.getQueryParams() != null) {
            request.getQueryParams().forEach(builder::queryParam);
        }

        URI uri = builder.build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(request.getRequestBody(), headers);

        return restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class).getBody();
    }

    private Object sendPostRequest(HttpRequestDTO request){


        return null;
    }

    @Override
    public String getType() {
        return FunctionType.HTTP.getName();
    }
}
