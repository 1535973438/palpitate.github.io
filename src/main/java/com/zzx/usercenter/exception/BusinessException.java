package com.zzx.usercenter.exception;

import com.zzx.usercenter.common.ErrorCode;
import lombok.Data;

import javax.swing.plaf.nimbus.NimbusStyle;

/**
 * 自定义异常
 */
@Data
public class BusinessException extends RuntimeException{
    private int code;
    private String description;
    public BusinessException(String message,int code,String description){
        super(message);
        this.code=code;
        this.description=description;
    }
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.description=errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode,String description){
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.description=description;
    }
}
