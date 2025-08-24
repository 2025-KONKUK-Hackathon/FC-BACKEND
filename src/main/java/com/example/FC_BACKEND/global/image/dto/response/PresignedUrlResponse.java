package com.example.FC_BACKEND.global.image.dto.response;

import com.example.FC_BACKEND.global.image.dto.request.PresignedUrlRequest;

import java.util.List;

public record PresignedUrlResponse(
        List<String> mediaUrl
) {
    public static PresignedUrlResponse of(List<String> urls){
        return new PresignedUrlResponse(urls);
    }
}
