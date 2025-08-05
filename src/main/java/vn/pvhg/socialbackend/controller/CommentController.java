package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    @PostMapping
    public ResponseEntity<Object> postComment(@PathVariable UUID postId) {
        return ResponseEntity.ok("");
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteComment(@PathVariable UUID postId) {
        return ResponseEntity.ok("");
    }
}
