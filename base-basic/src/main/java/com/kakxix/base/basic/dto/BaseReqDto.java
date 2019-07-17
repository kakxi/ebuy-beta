package com.kakxix.base.basic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseReqDto extends BaseDto {

    private static final long serialVersionUID = -3538533652336520486L;

    /**
     * 请求编号（UUID）
     */
    protected String reqNo;

    /**
     * 用户id
     */
    protected Long userId;

    protected Date reqDate;

    protected String versionCode;

    protected String versionName;

    protected String ip;

    protected String os;

    protected String deviceId;

    protected String longitude;

    protected String latitude;
}
