package com.noticeboard.notice.board.global.errors.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationErrors> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationErrors {

        private final String field;
        private final String message;

        public static ValidationErrors of(final FieldError fieldError) {
            return ValidationErrors.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}
