package com.example.FC_BACKEND.global.image.controller;

import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.image.dto.request.PresignedUrlRequest;
import com.example.FC_BACKEND.global.image.dto.response.PresignedUrlResponse;
import com.example.FC_BACKEND.global.image.service.ImageService;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription.*;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Tag(name = "이미지 업로드")
    @Operation(summary = "이미지 업로드 API", description = "mediaType을 통해 PresignedUrl을 발급받습니다.")
    @CustomExceptionDescription(UPLOAD_IMAGE)
    @PostMapping("upload")
    public BaseResponse<PresignedUrlResponse> createdUrls(@RequestBody PresignedUrlRequest req){
        return BaseResponse.ok(imageService.generatePresignedUrls(req.mediaType()),"이미지 업로드가 완료되었습니다.");
    }
}
