package com.example.FC_BACKEND.domain.post.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    @Tag(name = "게시글 관련 API")
    @Operation(summary = "게시글 작성")
    @CustomExceptionDescription(SwaggerResponseDescription.POST_CREATE)
    @PostMapping()
    public BaseResponse<Long> createPost(@LoginUserId @Parameter(hidden = true) Long userId, @RequestBody PostCreateRequest req){
        return BaseResponse.create(postService.createPost(userId, req.title(), req.content(), req.imageUrls(), req.part(),
                req.grade(), req.Topic(), req.affiliation()),"게시글 작성이 완료되었습니다.");
    }


}
