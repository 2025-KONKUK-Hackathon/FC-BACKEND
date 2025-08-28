package com.example.FC_BACKEND.domain.post.entity;

import com.example.FC_BACKEND.domain.post.constant.Affiliation;
import com.example.FC_BACKEND.domain.post.constant.Grade;
import com.example.FC_BACKEND.domain.post.constant.Part;
import com.example.FC_BACKEND.domain.post.constant.Topic;
import com.example.FC_BACKEND.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private Part part;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    private Affiliation affiliation;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder.Default
    private boolean isAnnouncement = false;

    public static Post create(User user, String title, String content, Part part,  Grade grade, Topic topic, Affiliation affiliation){
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .part(part)
                .grade(grade)
                .topic(topic)
                .affiliation(affiliation)
                .build();
    }

}
