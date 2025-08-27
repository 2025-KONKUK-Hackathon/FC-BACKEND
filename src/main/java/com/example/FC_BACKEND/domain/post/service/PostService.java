package com.example.FC_BACKEND.domain.post.service;

import com.example.FC_BACKEND.domain.comment.dto.response.CommentResponse;
import com.example.FC_BACKEND.domain.comment.entity.Comment;
import com.example.FC_BACKEND.domain.comment.repository.CommentCustomRepository;
import com.example.FC_BACKEND.domain.comment.repository.CommentCustomRepositoryImpl;
import com.example.FC_BACKEND.domain.comment.repository.CommentRepository;
import com.example.FC_BACKEND.domain.post.constant.Affiliation;
import com.example.FC_BACKEND.domain.post.constant.Grade;
import com.example.FC_BACKEND.domain.post.constant.Part;
import com.example.FC_BACKEND.domain.post.constant.Topic;
import com.example.FC_BACKEND.domain.post.dto.response.PostDetailResponse;
import com.example.FC_BACKEND.domain.post.dto.response.PostSummaryResponse;
import com.example.FC_BACKEND.domain.post.entity.Post;
import com.example.FC_BACKEND.domain.post.entity.PostImage;
import com.example.FC_BACKEND.domain.post.entity.PostScrap;
import com.example.FC_BACKEND.domain.post.repository.PostCustomRepositoryImpl;
import com.example.FC_BACKEND.domain.post.repository.PostImageRepository;
import com.example.FC_BACKEND.domain.post.repository.PostRepository;
import com.example.FC_BACKEND.domain.post.repository.PostScrapRepository;
import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.service.UserService;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.dto.SliceResponse;
import com.example.FC_BACKEND.global.exception.constant.PostErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.FC_BACKEND.global.exception.constant.PostErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
//TODO: 게시글 상세 조회, 게시글 전체 조회
public class PostService {

    private final PostRepository postRepository;

    private final PostCustomRepositoryImpl postCustomRepositoryImpl;

    private final PostScrapRepository postScrapRepository;

    private final UserService userService;

    private final PostImageRepository postImageRepository;

    private final CommentRepository commentRepository;

    private final CommentCustomRepositoryImpl commentCustomRepositoryImpl;

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

    public SliceResponse<PostSummaryResponse, Long> getAllPosts(Long cursorId, int size){

        Slice<PostSummaryResponse> postList = postRepository.findAllByCursorId(cursorId, size);

        return SliceResponse.from(postList);
    }

    public PostDetailResponse getPostsById(Long postId){
        Post post = findPostById(postId);

        User user = post.getUser();

        List<Comment> comments = commentRepository.findByPostId(postId);

        List<String> imageUrls = postImageRepository.findAllUrlByPostId(postId);

        return PostDetailResponse.builder()
                .writerId(user.getId())
                .writerName(user.getName())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .commentCount(comments.size())
                .imageUrls(imageUrls)
                .grade(post.getGrade().toString())
                .affiliation(post.getAffiliation().toString())
                .part(post.getPart().toString())
                .topic(post.getTopic().toString())
                .build();

    }

    public SliceResponse<CommentResponse, Long> getCommentsByCursorId(Long postId, Long cursorId, int size){

        Slice<CommentResponse> commentList = commentCustomRepositoryImpl.findByPostId(postId, cursorId, size);

        return SliceResponse.from(commentList);

    }

    //TODO: 게시물 스크랩 기능, 스크랩한 게시물 조회 기능

    @Transactional
    public void scrapPost(Long userId, Long postId){
        Post post = findPostById(postId);

        User user = userService.findUser(userId);

        Optional<PostScrap> postscrap = postScrapRepository.findByUserIdAndPostId(userId, postId);
        if(postscrap.isPresent()){
            throw new CustomException(POST_ALREADY_SCRAP);
        }

        postScrapRepository.save(PostScrap.create(user, post));

    }



}
