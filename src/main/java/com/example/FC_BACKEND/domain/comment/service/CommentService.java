package com.example.FC_BACKEND.domain.comment.service;

import com.example.FC_BACKEND.domain.comment.entity.Comment;
import com.example.FC_BACKEND.domain.comment.repository.CommentRepository;
import com.example.FC_BACKEND.domain.post.entity.Post;
import com.example.FC_BACKEND.domain.post.service.PostService;
import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.service.UserService;
import com.example.FC_BACKEND.global.exception.constant.PostErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.FC_BACKEND.global.exception.constant.PostErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;

    @Transactional
    public Long createComment(Long userId, Long postId, String content){

        User user = userService.findUser(userId);

        Post post = postService.findPostById(postId);

        Comment comment = commentRepository.save(Comment.create(user, post, content));

        return comment.getId();

    }

    @Transactional
    public void deleteComment(Long userId, Long commentId){
        Comment comment = findCommentById(commentId);

        User user = userService.findUser(userId);

        if(!user.equals(comment.getUser())){
            throw new CustomException(COMMENT_UNAUTHORIZED);
        }

        commentRepository.deleteById(commentId);
    }

    private Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
    }

}
