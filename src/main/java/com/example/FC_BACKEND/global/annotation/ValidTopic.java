package com.example.FC_BACKEND.global.annotation;

import com.example.FC_BACKEND.global.validator.PartValidator;
import com.example.FC_BACKEND.global.validator.TopicValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TopicValidator.class)
public @interface ValidTopic {
    String message() default "주제는 수강신청, 수업, 팀플, 아티클, 진로, 프로젝트, 스터디, 인턴, 기타 중에서 선택해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
