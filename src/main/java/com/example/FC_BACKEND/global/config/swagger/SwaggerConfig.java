package com.example.FC_BACKEND.global.config.swagger;

import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.exception.constant.ErrorCode;
import com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode;
import com.example.FC_BACKEND.global.response.BaseErrorResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "건국대학교 컴퓨터공학부 해커톤 4팀",
                description = "Springdoc을 이용한 Swagger API 문서입니다.",
                version = "1.0"
        )
)


@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI openAPI() {

                return new OpenAPI()
                        .components(new Components())
                        .addServersItem(new Server().url("/"))
                        ;


        }

        @Bean
        public OperationCustomizer customize() {
                return (Operation operation, HandlerMethod handlerMethod) -> {

                        CustomExceptionDescription customExceptionDescription = handlerMethod.getMethodAnnotation(
                                CustomExceptionDescription.class);

                        addGlobalErrorResponses(operation);
                        // CustomExceptionDescription 어노테이션 단 메소드 적용
                        if (customExceptionDescription != null) {
                                generateErrorCodeResponseExample(operation, customExceptionDescription.value());
                        }

                        return operation;
                };
        }

        private void generateErrorCodeResponseExample(
                Operation operation, SwaggerResponseDescription type) {

                ApiResponses responses = operation.getResponses();

                Set<ErrorCode> errorCodeList = type.getErrorCodeList();

                Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                        errorCodeList.stream()
                                .map(
                                        errorCode -> {
                                                return ExampleHolder.builder()
                                                        .holder(
                                                                getSwaggerExample(errorCode))
                                                        .code(errorCode.getHttpStatus())
                                                        .name(errorCode.toString())
                                                        .build();
                                        }
                                ).collect(groupingBy(ExampleHolder::getCode));
                addExamplesToResponses(responses, statusWithExampleHolders);
        }

        private Example getSwaggerExample(ErrorCode errorCode) {
                BaseErrorResponse errorResponse = BaseErrorResponse.of(errorCode);
                Example example = new Example();
                example.description(errorCode.getMessage());
                example.setValue(errorResponse);
                return example;
        }

        private void addExamplesToResponses(
                ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
                statusWithExampleHolders.forEach(
                        (status, v) -> {
                                Content content = new Content();
                                MediaType mediaType = new MediaType();
                                ApiResponse apiResponse = new ApiResponse();
                                v.forEach(
                                        exampleHolder -> {
                                                mediaType.addExamples(
                                                        exampleHolder.getName(), exampleHolder.getHolder());
                                        });
                                content.addMediaType("application/json", mediaType);
                                apiResponse.setDescription("");
                                apiResponse.setContent(content);
                                responses.addApiResponse(status.toString(), apiResponse);
                        });
        }

        private void addGlobalErrorResponses(Operation operation) {
                ApiResponses responses = operation.getResponses();

                for (GlobalErrorCode errorCode : GlobalErrorCode.values()) {
                        Example example = getSwaggerExample(errorCode);
                        MediaType mediaType = new MediaType().addExamples(errorCode.name(), example);
                        Content content = new Content().addMediaType("application/json", mediaType);

                        ApiResponse apiResponse = new ApiResponse()
                                .description(errorCode.getMessage())
                                .content(content);

                        responses.addApiResponse(String.valueOf(errorCode.getHttpStatus()), apiResponse);
                }
        }
}
