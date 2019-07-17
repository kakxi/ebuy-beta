/*
 * **********************************************************
 *
 * 原点系统
 *
 * ©2019 深圳瀚德法信区块链科技有限公司 保留所有权利
 *
 * **********************************************************
 */
package com.kakxix.base.basic.exception;

import com.kakxix.base.basic.enums.MessageCodeEnum;

import java.text.MessageFormat;

/**
 * 系统异常
 */
/*
 * 继承RuntimeException，不可修改
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -777939187502952549L;

    protected String errorCode;

    protected Object[] messageArgs;

    public SystemException(MessageCodeEnum messageCodeEnum) {
        this(messageCodeEnum, (Object)null);
    }

    public SystemException(MessageCodeEnum messageCodeEnum, Object...messageArgs) {
        this(messageCodeEnum, null, messageArgs);
    }

    public SystemException(MessageCodeEnum messageCodeEnum, Throwable cause, Object...messageArgs) {
        this(messageCodeEnum.getCode(), messageCodeEnum.getMessage(), cause, messageArgs);
    }

    public SystemException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public SystemException(String errorCode, String message, Throwable cause, Object...messageArgs) {
        super(message, cause);
        this.errorCode = errorCode;
        this.messageArgs = messageArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        if (messageArgs == null || messageArgs.length == 0) {
            return super.getMessage();
        } else {
            return MessageFormat.format(super.getMessage(), messageArgs);
        }
    }


    @Override
    public String toString() {
        return "调用错误：" + (errorCode == null ? "" : (",错误代码:" + errorCode)) + ",错误信息:" + getMessage();
    }
}
