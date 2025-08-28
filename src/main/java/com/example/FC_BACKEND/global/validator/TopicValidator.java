package com.example.FC_BACKEND.global.validator;

import com.example.FC_BACKEND.global.annotation.ValidTopic;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class TopicValidator implements ConstraintValidator<ValidTopic, String> {
    private static final Set<String> ALLOWED_TOPIC = Set.of("COURSE_REGISTRATION", "CLASS", "TEAM_PROJECT", "ARTICLE",
            "CARRER", "PROJECT", "STUDY", "INTERNSHIP", "ETC");

    @Override
    public boolean isValid(String topic, ConstraintValidatorContext constraintValidatorContext) {
        return ALLOWED_TOPIC.contains(topic);
    }
}
