package com.example.FC_BACKEND.domain.meeting.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    STUDY("STUDY"),
    PROJECT("PROJECT"),
    EVENT("EVENT"),
    FRIENDSHIP("FRIENDSHIP"),
    ETC("ETC")
    ;

    private final String description;
}
