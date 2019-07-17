/*
 * Copyright 2017 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.enums;

import com.kakxix.base.basic.util.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <P><P>
 */
@Getter
@AllArgsConstructor
public enum ProductCodeEnum implements BaseEnum {

    EBUY("EBUY", "易购");

    /** code **/
    private final String code;

    /** message **/
    private final String message;

    /**
     * <P><P>
     * @return
     */
    @Override
    public String toString() {
        return getCode();
    }

    /**
     * <P> <P>
     */
    public static ProductCodeEnum get(String code) {
        return EnumUtil.valueOf(ProductCodeEnum.class, code);
    }
}
