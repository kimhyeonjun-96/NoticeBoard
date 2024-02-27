package com.noticeboard.notice.board.global.errors.exception;

import com.noticeboard.notice.board.global.errors.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FaildToAuthenticatePasswordException extends RuntimeException {

    private final ErrorCode errorCode;
}
