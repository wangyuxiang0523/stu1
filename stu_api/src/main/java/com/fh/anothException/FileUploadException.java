package com.fh.anothException;

import com.fh.util.StatusCodes;

public class FileUploadException extends RuntimeException {

    private Integer code;

    public FileUploadException(StatusCodes status) {
        super(status.getMessage());
        this.code=status.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
