package com.zzx.usercenter.common;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * 返回工具类
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"ok");
    }
    public static BaseResponse error(ErrorCode code){
        return new BaseResponse<>(code.getCode(),null,code.getMessage(),code.getDescription());
    }
    public static BaseResponse error(ErrorCode code,String message,String description){
        return new BaseResponse<>(code.getCode(),null,message,description);
    }

    public static BaseResponse error(ErrorCode code,String description){
        return new BaseResponse<>(code.getCode(),null,code.getMessage(),description);
    }

    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse<>(code,null,message,description);
    }
}
