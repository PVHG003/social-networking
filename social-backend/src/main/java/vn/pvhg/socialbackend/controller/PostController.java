package vn.pvhg.socialbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import vn.pvhg.socialbackend.dto.request.PostRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.PostService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            UriComponentsBuilder uriComponentsBuilder,
            @RequestPart(name = "data", required = false) PostRequest req,
            @RequestPart(name = "files", required = false) List<MultipartFile> files) {
        if (req == null && files == null) {
            throw new IllegalStateException("Both request and files are null - invalid state");
        }

        PostResponse post = postService.createPost(req, files);
        ApiResponse<PostResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "Post created", post);
        URI uri = uriComponentsBuilder.buildAndExpand("/api/posts/{id}").expand(post.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(@PathVariable UUID postId,
                                                                @RequestBody PostRequest req) {
        PostResponse post = postService.updatePost(postId, req);
        ApiResponse<PostResponse> response = new ApiResponse<>(HttpStatus.OK, true, "Post updated", post);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.NO_CONTENT, true, "Post deleted", null);
        return new ResponseEntity<>(response, new HttpHeaders(), response.status());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable UUID postId) {
        PostResponse post = postService.getPostById(postId);
        ApiResponse<PostResponse> response = new ApiResponse<>(HttpStatus.OK, true, "Post found", post);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/explore")
    public ResponseEntity<ApiPagedResponse<PostResponse>> explore(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostResponse> posts = postService.getAllPosts(pageable);
        ApiPagedResponse<PostResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "Posts found",
                posts.getContent(),
                posts.getPageable().getPageNumber(),
                posts.getPageable().getPageSize(),
                posts.getTotalElements(),
                posts.getTotalPages());
        return ResponseEntity.ok(response);
    }
}