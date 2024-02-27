package com.noticeboard.notice.board.global.errors.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    FAILED_TO_AUTHENTICATE_PASSWORD(HttpStatus.EXPECTATION_FAILED, "패스워드 인증에 실패하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
