package com.example.FC_BACKEND.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private String url;

    public static PostImage of(Post post, String url) {
        return PostImage.builder()
                .post(post)
                .url(url)
                .build();
    }
}
