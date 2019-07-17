package com.kakxix.base.basic.dto;


import com.kakxix.base.basic.enums.MessageCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BaseRespDto extends BaseDto {
    private static final long serialVersionUID = 4173902142755769322L;

    /**
     * 请求编号
     */
    protected String reqNo;

    /**
     * 返回编号
     */
    protected String respNo;

    /**
     * 返回代码
     */
    protected String respCode;

    /**
     * 返回描述
     */
    protected String respMsg;

    public BaseRespDto() {
        this(MessageCodeEnum.SUCCESSCODE);
    }

    /**
     * @param respCode 代码
     * @param respMsg  描述
     */
    public BaseRespDto(String respCode, String respMsg) {
        super();
        this.respCode = respCode;
        this.respMsg = respMsg;
    }


    public BaseRespDto(MessageCodeEnum messageCodeEnum) {
        super();
        this.respCode = messageCodeEnum.getCode();
        this.respMsg = messageCodeEnum.getMessage();
    }

    /**
     * 串联reqNo
     *
     * @param reqGson
     */
    public BaseRespDto(BaseReqDto reqGson) {
        super();
        this.reqNo = reqGson.getReqNo();
        this.respNo = UUID.randomUUID().toString().replaceAll("-", "");
        this.initSuccessResp();
    }

    /**
     * 构建初始化成功返回
     */
    public void initSuccessResp() {
        respCode = MessageCodeEnum.SUCCESSCODE.getCode();
        respMsg = MessageCodeEnum.SUCCESSCODE.getMessage();
    }


    /**
     * <p>
     * 初始化成功
     * <p>
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseRespDto> T buildSuccess() {
        return build(MessageCodeEnum.SUCCESSCODE);
    }

    /**
     * <p>
     * 构建失败
     * <p>
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseRespDto> T build(MessageCodeEnum messageCodeEnum) {
        this.respCode = messageCodeEnum.getCode();
        this.respMsg = messageCodeEnum.getMessage();
        return (T) this;
    }

    /**
     * <p>
     * 构建失败
     * <p>
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseRespDto> T build(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
        return (T) this;
    }


    /**
     * 判断是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return MessageCodeEnum.SUCCESSCODE.getCode().equals(respCode);
    }
}
