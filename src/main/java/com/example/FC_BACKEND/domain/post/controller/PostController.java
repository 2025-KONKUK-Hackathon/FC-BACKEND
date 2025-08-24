package com.example.FC_BACKEND.domain.post.controller;

import com.example.FC_BACKEND.domain.comment.dto.request.CommentCreateRequest;
import com.example.FC_BACKEND.domain.comment.service.CommentService;
import com.example.FC_BACKEND.domain.post.dto.request.PostCreateRequest;
import com.example.FC_BACKEND.domain.post.service.PostService;
import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 작성")
    @CustomExceptionDescription(POST_CREATE)
    @PostMapping()
    public BaseResponse<Long> createPost(@LoginUserId @Parameter(hidden = true) Long userId, @RequestBody PostCreateRequest req){
        return BaseResponse.create(postService.createPost(userId, req.title(), req.content(), req.imageUrls(), req.part(),
                req.grade(), req.Topic(), req.affiliation()),"게시글 작성이 완료되었습니다.");
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 삭제")
    @CustomExceptionDescription(POST_DELETE)
    @DeleteMapping("{postId}")
    public BaseResponse<Void> deletePost(@LoginUserId @Parameter(hidden = true) Long userId, @PathVariable Long postId){
        postService.deletePost(userId, postId);
        return BaseResponse.ok("게시글 삭제가 완료되었습니다.");
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "댓글 작성")
    @CustomExceptionDescription(COMMENT_CREATE)
    @PostMapping("comments")
    public BaseResponse<Long> createComment(@LoginUserId @Parameter(hidden = true) Long userId, @RequestBody CommentCreateRequest req){
        return BaseResponse.create(commentService.createComment(userId, req.postId(), req.content()), "댓글 작성이 완료되었습니다." );
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "댓글 삭제")
    @CustomExceptionDescription(COMMENT_DELETE)
    @DeleteMapping("comments/{commentId}")
    public BaseResponse<Void> deleteComment(@LoginUserId @Parameter(hidden = true) Long userId, @PathVariable Long commentId){
        commentService.deleteComment(userId, commentId);
        return BaseResponse.ok("댓글 삭제가 완료되었습니다.");
    }
}
