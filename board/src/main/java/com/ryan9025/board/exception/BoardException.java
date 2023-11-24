package com.ryan9025.board.exception;

import com.ryan9025.board.code.ErrorCode;
import lombok.*;

@Getter
    public class BoardException extends RuntimeException{
    private String detailMessage;
    private ErrorCode errorCode;

    public BoardException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BoardException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = getDetailMessage();
    }

}
