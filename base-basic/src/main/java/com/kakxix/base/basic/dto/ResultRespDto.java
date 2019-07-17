/*
 * **********************************************************
 *
 * 原点系统
 *
 * ©2019 深圳瀚德法信区块链科技有限公司 保留所有权利
 *
 * **********************************************************
 */
package com.kakxix.base.basic.dto;

import com.kakxix.base.basic.enums.MessageCodeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Linyuning
 * @date 2019/2/22
 */
@Getter
@Setter
@NoArgsConstructor
public class ResultRespDto<T> extends BaseRespDto {

    private static final long serialVersionUID = 6346298084506018936L;
    private T data;

    public ResultRespDto(T data) {
        this.data = data;
    }

    public ResultRespDto(String respCode, String respMsg) {
        super(respCode, respMsg);
    }

    public ResultRespDto(MessageCodeEnum messageCodeEnum) {
        super(messageCodeEnum);
    }
}
