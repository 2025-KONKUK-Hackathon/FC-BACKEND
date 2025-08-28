package com.example.FC_BACKEND.global.annotation;

import com.example.FC_BACKEND.global.validator.GradeValidator;
import com.example.FC_BACKEND.global.validator.MeetingCategoryValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MeetingCategoryValidator.class)
public @interface ValidCategory {
    String message() default "카테고리는 스터디, 프로젝트, 행사, 친목, 기타 중에서 선택해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
