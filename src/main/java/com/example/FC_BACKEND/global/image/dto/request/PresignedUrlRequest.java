package com.example.FC_BACKEND.global.image.dto.request;

import java.util.List;

public record PresignedUrlRequest(
        List<String> mediaType
) {
}
