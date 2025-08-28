package com.example.FC_BACKEND.global.validator;

import com.example.FC_BACKEND.global.annotation.ValidCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class MeetingCategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private static final Set<String> ALLOWED_CATEGORY = Set.of("STUDY", "PROJECT", "EVENT", "FRIENDSHIP", "ETC");


    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        return ALLOWED_CATEGORY.contains(category);
    }
}
