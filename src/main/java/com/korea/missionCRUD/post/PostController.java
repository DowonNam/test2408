package com.korea.missionCRUD.post;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    // Post 생성 요청 객체
    @Getter
    @Setter
    public static class PostCreateRequest {
        private String title;
        private String content;
    }

    // Post 업데이트 요청 객체
    @Getter
    @Setter
    public static class PostUpdateRequest {
        private Long id;
        private String title;
        private String content;
    }

    // Post 삭제 요청 객체
    @Getter
    @Setter
    public static class PostDeleteRequest {
        private Long id;
    }

    @Getter
    @Setter
    public static class PagingRequest {  // 정적 클래스(static class)로 변경
        private int page;
        private int size;
    }

    @PostMapping
    public ResponseEntity<?> postCreate(@RequestBody PostCreateRequest request) {
        try {
            Post post = postService.postCreate(request.getTitle(), request.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> postUpdate(@RequestBody PostUpdateRequest request) {
        try {
            Post post = postService.postUpdate (request.getId (), request.getTitle(), request.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> postDelete(@RequestBody PostDeleteRequest request) {
        try {
            postService.postDelete (request.getId ());
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> postAll(@RequestHeader("page") int page,
                                     @RequestHeader("size") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Post> postList = postService.postListGet(pageable);
            return ResponseEntity.status(HttpStatus.OK).body(postList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving posts: " + e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> postGet(@RequestHeader("id") Long id) {
        try {
            Post post = postService.postGet(id);
            if (post != null) {
                return ResponseEntity.status(HttpStatus.OK).body(post);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
            }
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 오류를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving post: " + e.getMessage());
        }
    }



}
