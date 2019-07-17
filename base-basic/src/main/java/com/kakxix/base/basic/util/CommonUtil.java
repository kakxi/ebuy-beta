/*
 * **********************************************************
 *
 * 原点系统
 *
 * ©2019 深圳瀚德法信区块链科技有限公司 保留所有权利
 *
 * **********************************************************
 */
package com.kakxix.base.basic.util;

import java.util.UUID;

/**
 * @Class CommonUtil
 * @Author Linyuning
 * @Date 2019/2/25 15:43
 */
public class CommonUtil {

    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static long getCurrentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }
}
