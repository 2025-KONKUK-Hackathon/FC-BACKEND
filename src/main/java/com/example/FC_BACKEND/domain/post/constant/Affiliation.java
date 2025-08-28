package com.example.FC_BACKEND.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Affiliation {
    COMPUTER("COMPUTER"),
    SMART_ICT("SMART_ICT"),
    DOUBLE_MINOR("DOUBLE_MINOR"),
    UNDECLARED("UNDECLARED"),
    TRANSFER("TRANSFER");

    ;
    private final String description;
}
