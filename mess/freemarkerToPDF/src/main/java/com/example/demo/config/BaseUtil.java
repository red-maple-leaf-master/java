package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
public class BaseUtil {

    /**
     * @描述：文件转byte[]
     * @返回：byte[]
     * @作者：zhongjy
     * @时间：2019年7月15日 下午10:19:18
     */
    public static byte[] file2byte(File file) {
        FileInputStream fileInputStream = null;
        byte[] bFile = null;
        try {
            bFile = new byte[(int) file.length()];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }

        }
        return bFile;


    }
}

