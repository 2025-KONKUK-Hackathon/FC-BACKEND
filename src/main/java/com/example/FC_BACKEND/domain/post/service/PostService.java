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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
