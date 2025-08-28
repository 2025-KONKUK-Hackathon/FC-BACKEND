package com.example.FC_BACKEND.global.annotation;

import com.example.FC_BACKEND.global.validator.GradeValidator;
import com.example.FC_BACKEND.global.validator.PartValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PartValidator.class)
public @interface ValidPart {
    String message() default "파트는 서버, 웹, 기타 중에서 선택해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
