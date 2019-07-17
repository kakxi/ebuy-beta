/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.experimental.UtilityClass;

import java.net.InetAddress;
import java.util.UUID;

/**
 * 类SystemUtil的实现描述：操作系统层面动作
 *
 * @author haozhenjie 2018/4/27 17:12
 */
@UtilityClass
public class SystemUtil {

    /**
     * 获取本机IP
     *
     * @return
     */
    public static final String getLocalIp() {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException("[local-ip] an exception occured when get local ip address", e);
        }

        return ip;
    }

    /**
     * 获取UUID
     */
    public static final String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
