package com.fh.util;

public enum StatusCodes {
    HAVE_NO_POWER(403,"当前用户没有权限"),
    THIS_CAN_NOT_FIND(404,"页面没有找到"),
    SERVER_TIME_OUT(303,"服务器连接超时"),
    SERVER_CONNECTION_ERROR(304,"服务器连接异常"),
    ERROR_(305,"未知异常"),
    SUCCESS(200,"访问成功"),


    IMG_MAX_SIZE(701,"文件超出最大限度"),
    IMG_UPLOAD_ERROR(702,"文件上传失败")
    ;



    StatusCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
