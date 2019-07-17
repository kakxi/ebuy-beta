package com.kakxix.base.basic.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class sensitiveWordUtil {
    private static Map<String, String> sensitiveWordMap = null;

    public static Map<String, String> getSensitiveWordMap() {
        if (sensitiveWordMap == null) {
            sensitiveWordMap = new HashMap<String, String>();
            String path = sensitiveWordUtil.class.getClassLoader().getResource("templates/sensitiveWord.csv").getPath();
            File file = new File(path);

            BufferedReader br = null;
            String strLine = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                while ((strLine = br.readLine()) != null) {
                    sensitiveWordMap.put(strLine, strLine);
                }
            } catch (Exception e) {
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return sensitiveWordMap;
    }

    public static void main(String[] args) {
        Map<String, String> map = getSensitiveWordMap();
        System.out.println(map);
    }

}
