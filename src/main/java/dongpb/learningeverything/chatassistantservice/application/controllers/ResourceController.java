package dongpb.learningeverything.chatassistantservice.application.controllers;

import dongpb.learningeverything.chatassistantservice.domain.resource.ResourceService;
import dongpb.learningeverything.chatassistantservice.domain.resource.ResourceDTO;
import dongpb.learningeverything.chatassistantservice.domain.resource.http.HttpFunctionService;
import dongpb.learningeverything.chatassistantservice.domain.resource.http.HttpRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/resource")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;
    private final HttpFunctionService httpFunctionService;

    @PostMapping()
    public ResponseEntity<Object> createResource(@RequestBody ResourceDTO request){
        return ResponseEntity.ok(resourceService.create(request));
    }

    @PutMapping("/{code}")
    public ResponseEntity<Object> updateResource(
            @PathVariable("code") String code,
            @RequestBody ResourceDTO request){
        request.setResourceCode(code);
        return ResponseEntity.ok(resourceService.update(request));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResourceDTO> getResource(@PathVariable String code){
        return ResponseEntity.ok(resourceService.getByCode(code));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteResource(@PathVariable("code") String code){
        return ResponseEntity.ok(resourceService.delete(code));
    }

    @GetMapping("")
    public ResponseEntity<Object> sendRequest(@RequestBody HttpRequestDTO request){
        return ResponseEntity.ok(httpFunctionService.sendRequest(request));
    }
}
