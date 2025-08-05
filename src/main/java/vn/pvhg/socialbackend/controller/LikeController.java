package vn.pvhg.socialbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/like")
public class LikeController {
    @PostMapping
    public ResponseEntity<Object> likePost(@PathVariable String postId) {
        return ResponseEntity.ok("");
    }

    @DeleteMapping
    public ResponseEntity<Object> unlikePost(@PathVariable String postId) {
        return ResponseEntity.ok("");
    }
}
