/*
 * Copyright 2018 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.kakxix.base.basic.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * 文件处理类
 * 
 * @author maike.lin
 */

@Slf4j
public class FileTool {

    /**
     * 检查并创建文件夹
     * 
     * @param directoryName
     */
    public static void createFolder(String directoryName) {
        File theDir = new File(directoryName);
        if (!theDir.exists()) {
            log.info("directory {} not exist,creating.", directoryName);
            boolean result = false;
            try {
                theDir.mkdirs();
                result = true;
            } catch (SecurityException e) {
                log.error("directory {} create fail:{}.", directoryName, e);
            }
            if (result) {
                log.info("dir created:" + theDir.exists());
            }
        }
    }

    /**
     * 按章行读取指定的配置文件
     * 
     * @param inputStream
     * @return
     */
    public static String readFileByLines(InputStream inputStream) {

        BufferedReader reader = null;
        StringBuffer str = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                str.append(tempString.trim());
            }
            reader.close();
            return str.toString();
        } catch (IOException e) {
            log.error("read file error", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    log.error("reader.close error", e1);
                }
            }
        }
        return null;
    }

    /**
     * 根据当前日期和配置的日期格式生成文件路径
     * 
     * @param dir
     * @return
     */
    public static String formatDir(String dir) {
        if (StringUtils.isNotBlank(dir) && dir.contains("/")) {
            String[] paths = dir.split("/");
            int length = paths.length;
            if (length > 0) {
                for (String format : paths) {
                    if (format.startsWith("{") && format.endsWith("}")) {
                        dir = dir.replace(format, DateFormatUtils.format(DateUtil.addDate(new Date(), 0), format
                                .replace("{", "").replace("}", "")));
                    }
                }
            }
        }
        return dir;
    }

    /**
     * @param urlPath 图片路径
     * @return
     */
    public static InputStream downLoadLinePicture(String urlPath) {
        if (StringUtils.isEmpty(urlPath)) {
            return null;
        }
        InputStream inStream = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            conn.connect();
            inStream = conn.getInputStream();//通过输入流获取图片数据
        } catch (Exception e) {
            log.warn("downLoadLinePicture error:" + e);
        }
        return inStream;

    }

}
