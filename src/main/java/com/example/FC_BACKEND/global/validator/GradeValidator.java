package com.example.FC_BACKEND.global.validator;

import com.example.FC_BACKEND.global.annotation.ValidGrade;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class GradeValidator implements ConstraintValidator<ValidGrade, String> {

    private static final Set<String> ALLOWED_GRADE = Set.of("FIRST", "SECOND", "THIRD", "UNDECLARED", "TRANSFER");

    @Override
    public boolean isValid(String grade, ConstraintValidatorContext constraintValidatorContext) {
        return ALLOWED_GRADE.contains(grade);
    }
}
