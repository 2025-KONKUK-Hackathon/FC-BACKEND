package com.example.FC_BACKEND.global.image.service;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import com.example.FC_BACKEND.global.config.S3Config;
import com.example.FC_BACKEND.global.exception.constant.ImageErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import com.example.FC_BACKEND.global.image.dto.response.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import java.util.*;

import static com.example.FC_BACKEND.global.exception.constant.ImageErrorCode.*;

@Service
@RequiredArgsConstructor
public class ImageService {
    public static final Set<String> ALLOWED_MEDIA_TYPES = Set.of(
            "image/jpg", "image/jpeg", "image/png", "image/webp"
    );

    private final S3Presigner s3Presigner;
    private final S3Config s3Config;

    public PresignedUrlResponse generatePresignedUrls(List<String> fileTypes) {
        if (fileTypes == null || fileTypes.isEmpty()) {
            throw new CustomException(UNSUPPORTED_MEDIA_TYPE);
        }

        List<String> urls = new ArrayList<>(fileTypes.size());

        for (String raw : fileTypes) {
            if(!raw.contains("/")) {
                throw new CustomException(UNSUPPORTED_MEDIA_TYPE);
            }
            if(!ALLOWED_MEDIA_TYPES.contains(raw)){
                throw new CustomException(UNSUPPORTED_IMAGE_TYPE);
            }
            if (!raw.startsWith("image/")) {
                throw new CustomException(NOT_IMAGE);
            }

            String extension = getExtensionFromMediaType(raw);
            String key = "image/" + UUID.randomUUID() + "." + extension;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(s3Config.getBucket())
                    .key(key)
                    .build();

            PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(b -> b
                    .putObjectRequest(putObjectRequest)
                    .signatureDuration(Duration.ofMinutes(10)));

            urls.add(presignedRequest.url().toString());
        }

        return PresignedUrlResponse.of(urls);
    }

    private String getExtensionFromMediaType(String mimeType) {
        int slashIndex = mimeType.lastIndexOf('/');
        if (slashIndex == -1 || slashIndex == mimeType.length() - 1) {
            throw new CustomException(UNSUPPORTED_MEDIA_TYPE);
        }
        return mimeType.substring(slashIndex + 1).toLowerCase(Locale.ROOT);
    }
}
