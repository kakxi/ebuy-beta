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

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Class StreamUtil
 * @Author Linyuning
 * @Date 2019/2/28 15:56
 */
@Slf4j
public class StreamUtil {

    public static void close(Closeable stream) {
        try {
            if (stream != null)
                stream.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


}
