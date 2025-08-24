package com.example.FC_BACKEND.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Topic {
    COURSE_REGISTRATION("COURSE_REGISTRATION"),
    CLASS("CLASS"),
    TEAM_PROJECT("TEAM_PROJECT"),
    ARTICLE("ARTICLE"),
    CAREER("CAREER"),
    PROJECT("PROJECT"),
    STUDY("STUDY"),
    INTERNSHIP("INTERNSHIP"),
    ANNOUNCEMENT("ANNOUNCEMENT"),
    ETC("ETC");

    private final String description;
}
