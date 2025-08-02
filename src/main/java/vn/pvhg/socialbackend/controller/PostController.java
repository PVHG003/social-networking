package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import vn.pvhg.socialbackend.dto.request.PostRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.service.PostService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostResponse> response = postService.getAllPosts(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable UUID id) {
        PostResponse response = postService.getPostById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostRequest requestForm,
                                             UriComponentsBuilder uriComponentsBuilder) {
        PostResponse response = postService.createPost(requestForm);
        URI uri = uriComponentsBuilder.buildAndExpand("/api/posts/{id}").expand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/{id}/uploads")
    public ResponseEntity<Object> uploadMedias(@PathVariable UUID id,
                                               @RequestPart("files") List<MultipartFile> files,
                                               UriComponentsBuilder uriComponentsBuilder) {
        PostResponse response = postService.uploadMedias(id, files);
        URI uri = uriComponentsBuilder.buildAndExpand("/api/posts/{id}").expand(id).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
