package com.example.FC_BACKEND.global.validator;

import com.example.FC_BACKEND.global.annotation.ValidPart;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class PartValidator implements ConstraintValidator<ValidPart, String> {
    private static final Set<String> ALLOWED_PART = Set.of("SERVER", "WEB", "ETC");

    @Override
    public boolean isValid(String part, ConstraintValidatorContext constraintValidatorContext) {
        if(part == null || part.isBlank()){
            return true;
        }
        return ALLOWED_PART.contains(part);
    }
}
