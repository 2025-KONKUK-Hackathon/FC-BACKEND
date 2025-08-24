package com.example.FC_BACKEND.domain.post.service;

import com.example.FC_BACKEND.domain.post.constant.Affiliation;
import com.example.FC_BACKEND.domain.post.constant.Grade;
import com.example.FC_BACKEND.domain.post.constant.Part;
import com.example.FC_BACKEND.domain.post.constant.Topic;
import com.example.FC_BACKEND.domain.post.entity.Post;
import com.example.FC_BACKEND.domain.post.entity.PostImage;
import com.example.FC_BACKEND.domain.post.repository.PostCustomRepositoryImpl;
import com.example.FC_BACKEND.domain.post.repository.PostImageRepository;
import com.example.FC_BACKEND.domain.post.repository.PostRepository;
import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.service.UserService;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.exception.constant.PostErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.FC_BACKEND.global.exception.constant.PostErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    private final PostCustomRepositoryImpl postCustomRepositoryImpl;

    private final UserService userService;
    private final PostImageRepository postImageRepository;

    @Transactional
    public Long createPost(Long userId, String title, String content, List<String> imageUrls,
                           String part, String grade, String topic, String affiliation){

        User user = userService.findUser(userId);

        Post post = postRepository.save(Post.create(user, title, content, Part.valueOf(part),
                Grade.valueOf(grade), Topic.valueOf(topic), Affiliation.valueOf(affiliation)));

        List<String> urls = imageUrls != null ? imageUrls : List.of();
        if (!urls.isEmpty()) {

            List<PostImage> images = new ArrayList<>(urls.size());
            for (String url : urls) {
                images.add(PostImage.of(post, url));
            }
            postImageRepository.saveAll(images);
        }

        return post.getId();
    }

    @Transactional
    public void deletePost(Long userId, Long postId){
        List<PostImage> images = postImageRepository.findAllByPostId(postId);
        if(!images.isEmpty()){
            postImageRepository.deleteAll(images);
        }

        Post post = findPostById(postId);

        User user =  userService.findUser(userId);

        if(!post.getUser().equals(user)){
            throw new CustomException(POST_UNAUTHORIZED);
        }

        postRepository.deleteById(postId);
    }

    public Post findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(POST_NOT_FOUND));
    }
}
