package com.example.FC_BACKEND.global.auth.jwt;

import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUserId.class) &&
               parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validateAuthentication(authentication);

        return getUserIdFromAuthentication(authentication);
    }

    private void validateAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(GlobalErrorCode.UNAUTHORIZED);
        }
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        if (!(authentication instanceof JwtTokenAuthentication)) {
            throw new CustomException(GlobalErrorCode.AUTHENTICATION_SETTING_FAIL);
        }
        return ((JwtTokenAuthentication) authentication).getUserId();
    }
}

