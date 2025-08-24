package com.example.FC_BACKEND.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Part {
    WEB("WEB"),
    SERVER("SERVER"),
    ETC("ETC")
    ;

    private final String description;
}
