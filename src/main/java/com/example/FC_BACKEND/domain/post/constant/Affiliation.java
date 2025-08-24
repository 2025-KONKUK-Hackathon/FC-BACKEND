package com.example.FC_BACKEND.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Affiliation {
    COMPUTER("COMPUTER"),
    SMART_ICT("SMART_ICT"),
    DOUBLE_MAJOR("DOUBLE_MAJOR"),
    UNDECLARED("UNDECLARED"),
    TRANSFER("TRANSFER");

    ;
    private final String description;
}
