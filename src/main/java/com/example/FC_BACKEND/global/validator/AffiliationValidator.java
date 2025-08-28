package com.example.FC_BACKEND.global.validator;

import com.example.FC_BACKEND.global.annotation.ValidAffiliation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;

public class AffiliationValidator implements ConstraintValidator<ValidAffiliation, String> {

    private static final Set<String> ALLOWED_AFFILIATION = Set.of("COMPUTER", "SMART_ICT", "DOUBLE_MINOR", "UNDECLARED", "TRANSFER");

    @Override
    public boolean isValid(String affiliation, ConstraintValidatorContext constraintValidatorContext) {
        if(affiliation == null || affiliation.isBlank()){
            return true;
        }
        return ALLOWED_AFFILIATION.contains(affiliation);
    }
}
