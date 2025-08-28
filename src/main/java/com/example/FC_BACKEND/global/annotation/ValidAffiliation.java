package com.example.FC_BACKEND.global.annotation;

import com.example.FC_BACKEND.global.validator.AffiliationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AffiliationValidator.class)
@Documented
public @interface ValidAffiliation {
    String message() default "소속은 컴공, 스융공, 다/부전공, 편입, 자율전공 중에서 선택해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
