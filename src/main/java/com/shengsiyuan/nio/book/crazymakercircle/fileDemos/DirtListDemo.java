package com.shengsiyuan.nio.book.crazymakercircle.fileDemos;

import com.shengsiyuan.nio.book.crazymakercircle.util.IOUtil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.File;
import java.io.IOException;

public class DirtListDemo {

    public static void main(String[] args) {
        String path = IOUtil.getResourcePath("/");
        try {
            listFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void listFile(String path) throws IOException {

        if (path == null) {
            return;
        }

        File pFile = new File(path);
        if (pFile.isFile()) {
            //文件：输出完整名称
            Logger.debug("File:" + pFile.getCanonicalPath());
        } else if (pFile.isDirectory()) {
            //目录：输出完整名称
            Logger.debug("Direcotry:" + pFile.getCanonicalPath());
            File[] subFiles = pFile.listFiles();
            if (subFiles == null) {
                return ;
            }
            // 目录：递归遍历每一个下级File对象
            for (File file : subFiles) {
                listFile(file.getCanonicalPath());
            }
        } else {
            Logger.debug("error:" + pFile.getCanonicalPath());
        }
    }
}
