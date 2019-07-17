/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.annotation;


import com.kakxix.base.basic.enums.SensitiveType;

import java.lang.annotation.*;

/**
 * ======================================================== 自定义日志注解
 *
 * @author AngleSuper.Wei
 * @date 2017/1/23 ========================================================
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveInfo {

    /**
     * getType
     *
     * @return
     */
    SensitiveType type();
}
