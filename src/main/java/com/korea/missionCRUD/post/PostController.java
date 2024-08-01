package com.korea.missionCRUD.post;

import com.korea.missionCRUD.board.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Getter
    @Setter
    public static class PostRequest {
        private Long id;
        private String title;
        private String content;
    }

    @PostMapping
    public ResponseEntity<?> postCreate(@RequestBody PostRequest request) {
        try {
            Post post = postService.postCreate(request.getTitle(), request.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> postUpdate(@RequestBody PostRequest request) {
        try {
            Post post = postService.postUpdate (request.getId (), request.getTitle(), request.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> postDelete(@RequestBody PostRequest request) {
        try {
            postService.postDelete (request.getId ());
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> postAll() {
        try {
            List<Post> postList = postService.postListGet ();
            return ResponseEntity.status(HttpStatus.OK).body(postList);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }



}
