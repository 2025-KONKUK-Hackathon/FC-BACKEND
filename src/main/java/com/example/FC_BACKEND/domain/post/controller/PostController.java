package com.example.FC_BACKEND.domain.post.controller;

import com.example.FC_BACKEND.domain.comment.dto.request.CommentCreateRequest;
import com.example.FC_BACKEND.domain.comment.dto.response.CommentResponse;
import com.example.FC_BACKEND.domain.comment.service.CommentService;
import com.example.FC_BACKEND.domain.post.dto.request.PostCreateRequest;
import com.example.FC_BACKEND.domain.post.dto.response.PostDetailResponse;
import com.example.FC_BACKEND.domain.post.dto.response.PostSummaryResponse;
import com.example.FC_BACKEND.domain.post.service.PostService;
import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.dto.SliceResponse;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
    public BaseResponse<Long> createPost(@LoginUserId @Parameter(hidden = true) Long userId, @RequestBody @Valid PostCreateRequest req){
        return BaseResponse.create(postService.createPost(userId, req.title(), req.content(), req.imageUrls(), req.part(),
                req.grade(), req.topic(), req.affiliation()),"게시글 작성이 완료되었습니다.");
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

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 목록 조회")
    @CustomExceptionDescription(COMMON)
    @GetMapping()
    public BaseResponse<SliceResponse<PostSummaryResponse, Long>> getAllPosts(
            @RequestParam(required = false) Long cursorId, @RequestParam(required = false, defaultValue = "10") int size
    ){
        return BaseResponse.ok(postService.getAllPosts(cursorId, size),"게시글 목록 조회에 성공하였습니다.");
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 상세 조회")
    @CustomExceptionDescription(POST_DETAIL)
    @GetMapping("{postId}")
    public BaseResponse<PostDetailResponse> getPostDetail(
            @LoginUserId @Parameter(hidden = true) Long userId,
            @PathVariable Long postId){
        return BaseResponse.ok(postService.getPostsById(userId, postId),"게시물 상세조회에 성공하였습니다.");
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글의 댓글 조회", description = "게시글 상세 조회에서 댓글 목록을 조회합니다.")
    @CustomExceptionDescription(COMMON)
    @GetMapping("{postId}/comments")
    public BaseResponse<SliceResponse<CommentResponse, Long>> getComments(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(required = false, name = "cursor") Long cursorId,
            @RequestParam(defaultValue = "10") int size
    ){
        return BaseResponse.ok(postService.getCommentsByCursorId(postId, cursorId, size),"댓글 조회에 성공하였습니다.");
    }

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 스크랩")
    @CustomExceptionDescription(POST_SCRAP)
    @PostMapping("scraps")
    public BaseResponse<Void> scrapPost(
            @LoginUserId @Parameter(hidden = true) Long userId,
            @RequestParam Long postId
    ){
        postService.scrapPost(userId, postId);
        return BaseResponse.ok("게시물 스크랩에 성공하였습니다.");
    }

    @Tag(name = "마이페이지 관련 API")
    @Operation(summary = "내가 스크랩한 게시글 조회")
    @CustomExceptionDescription(COMMON)
    @GetMapping("scraps")
    public BaseResponse<SliceResponse<PostSummaryResponse, Long>> getAllScaps(
            @LoginUserId @Parameter(hidden = true) Long userId,
            @RequestParam(required = false, name = "cursor") Long cursorId,
            @RequestParam(defaultValue = "10") int size
    ){
        return BaseResponse.ok(postService.getScrapPost(userId,  cursorId, size),"스크랩한 게시물 조회에 성공하였습니다.");
    }


}
