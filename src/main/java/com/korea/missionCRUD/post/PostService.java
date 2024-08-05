package com.korea.missionCRUD.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post postCreate(String title, String content){
        Post post = new Post ();

        post.setTitle (title);
        post.setContent (content);
        post.setAuthor ("DW");
        post.setPostDate (LocalDateTime.now ());

        return postRepository.save (post);
    }

    public Post postUpdate(Long postId, String title, String content){
        Post post = postRepository.findById (postId).get ();
        post.setTitle (title);
        post.setContent (content);

        return postRepository.save (post);
    }

    public void postDelete(Long postId){
        Post post = postRepository.findById (postId).get ();
        postRepository.delete (post);
    }

    public Page<Post> postListGet(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post postGet(Long postId){
        Post post = postRepository.findById (postId).get ();
        return post;
    }
}
